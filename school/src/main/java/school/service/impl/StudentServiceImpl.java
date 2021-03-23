package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import school.model.entity.GroupEntity;
import school.model.entity.StudentEntity;
import school.model.service.StudentServiceModel;
import school.repository.GroupRepository;
import school.repository.StudentRepository;
import school.repository.UserRepository;
import school.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends BaseService implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public StudentServiceImpl(ModelMapper modelMapper,
                              StudentRepository studentRepository,
                              GroupRepository groupRepository, UserRepository userRepository) {
        super(modelMapper);
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addStudent(StudentServiceModel serviceModel) {
        StudentEntity studentEntity = modelMapper.map(serviceModel, StudentEntity.class);
        studentEntity.setId(null);
        GroupEntity groupEntity = groupRepository.findById(serviceModel.getGroupId()).orElseThrow();
        studentEntity.setGroup(groupEntity);
        studentRepository.saveAndFlush(studentEntity);
    }

    @Override
    public List<StudentServiceModel> getStudentsByClassId(Long groupId) {
        return studentRepository.findAllByGroupId(groupId)
                .stream()
                .map(e-> modelMapper.map(e,StudentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentServiceModel getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(e-> modelMapper.map(e, StudentServiceModel.class))
                .orElseThrow();
    }

    @Override
    public void editStudent(StudentServiceModel serviceModel) {
        StudentEntity entity = studentRepository.findById(serviceModel.getId()).orElseThrow();
        String firstName = serviceModel.getFirstName();
        String middleName = serviceModel.getMiddleName();
        String lastName = serviceModel.getLastName();
        Long userId= serviceModel.getUserId();
        if (!firstName.isEmpty()){
            entity.setFirstName(firstName);
        }
        if (!middleName.isEmpty()){
            entity.setMiddleName(middleName);
        }
        if (!lastName.isEmpty()){
            entity.setLastName(lastName);
        }
        if (userId != null){
            entity.setUser(userRepository.findById(userId).orElseThrow());
        }
        studentRepository.save(entity);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
