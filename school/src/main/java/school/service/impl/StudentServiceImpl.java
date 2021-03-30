package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import school.constants.enumuration.AuthorityEnum;
import school.exception.StudentIdNotFoundException;
import school.exception.StudentUsernameNoFound;
import school.model.entity.StudentEntity;
import school.model.service.StudentServiceModel;
import school.model.service.UserServiceModel;
import school.repository.StudentRepository;
import school.repository.UserRepository;
import school.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends BaseService implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentServiceImpl(ModelMapper modelMapper,
                              StudentRepository studentRepository, UserRepository userRepository) {
        super(modelMapper);
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StudentServiceModel addStudent(StudentServiceModel serviceModel) {
        StudentEntity studentEntity = modelMapper.map(serviceModel, StudentEntity.class);
        StudentEntity saved = studentRepository.saveAndFlush(studentEntity);
        return modelMapper.map(saved,StudentServiceModel.class);
    }

    @Override
    public List<StudentServiceModel> getStudentsByClassId(Long groupId) {
        return studentRepository.findAllByGroupId(groupId)
                .stream()
                .map(e -> modelMapper.map(e, StudentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentServiceModel getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(e -> modelMapper.map(e, StudentServiceModel.class))
                .orElseThrow(StudentIdNotFoundException::new);
    }

    @Override
    public StudentServiceModel editStudent(StudentServiceModel serviceModel) {
        return addStudent(serviceModel);
    }

    @Override
    public boolean deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return true;
    }

    @Override
    public List<UserServiceModel> getAllFreeStudentUsers() {
        return userRepository.findAllByAuthority(AuthorityEnum.STUDENT.name())
                .stream()
                .filter(user -> !studentRepository.existsByUser_Id(user.getId()))
                .map(e -> modelMapper.map(e, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentServiceModel getStudentByUserUsername(String username) {
        return this.studentRepository.findByUser_Username(username)
                .map(e -> modelMapper.map(e, StudentServiceModel.class))
                .orElseThrow(StudentUsernameNoFound::new);
    }
}
