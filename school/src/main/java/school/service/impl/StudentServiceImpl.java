package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import school.model.entity.GroupEntity;
import school.model.entity.StudentEntity;
import school.model.service.StudentServiceModel;
import school.model.service.UserServiceModel;
import school.repository.GroupRepository;
import school.repository.StudentRepository;
import school.service.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends BaseService implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentServiceImpl(ModelMapper modelMapper,
                              StudentRepository studentRepository,
                              GroupRepository groupRepository) {
        super(modelMapper);
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void addStudent(StudentServiceModel serviceModel) {
        StudentEntity studentEntity = modelMapper.map(serviceModel, StudentEntity.class);
        studentEntity.setId(null);
        GroupEntity groupEntity = groupRepository.findById(serviceModel.getId()).orElseThrow();
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
        if (!firstName.isEmpty()){
            entity.setFirstName(firstName);
        }
        if (!middleName.isEmpty()){
            entity.setMiddleName(middleName);
        }
        if (!lastName.isEmpty()){
            entity.setLastName(lastName);
        }
        studentRepository.save(entity);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
