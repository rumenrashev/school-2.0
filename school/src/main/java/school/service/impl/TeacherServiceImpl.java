package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.model.entity.GroupEntity;
import school.model.entity.SubjectEntity;
import school.model.entity.TeacherEntity;
import school.model.service.TeacherServiceModel;
import school.repository.SubjectRepository;
import school.repository.TeacherRepository;
import school.repository.UserRepository;
import school.service.TeacherService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl extends BaseService implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public TeacherServiceImpl(ModelMapper modelMapper, TeacherRepository teacherRepository, UserRepository userRepository, SubjectRepository subjectRepository) {
        super(modelMapper);
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void addTeacher(TeacherServiceModel serviceModel) {
        TeacherEntity entity = modelMapper.map(serviceModel, TeacherEntity.class);
        entity.setId(null);
        teacherRepository.save(entity);
    }

    @Override
    public List<TeacherServiceModel> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(e-> modelMapper.map(e,TeacherServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void editTeacher(TeacherServiceModel serviceModel) {
        TeacherEntity entity = teacherRepository.findById(serviceModel.getId()).orElseThrow();
        String firstName = serviceModel.getFirstName();
        String middleName = serviceModel.getMiddleName();
        String lastName = serviceModel.getLastName();
        Long userId = serviceModel.getUserId();
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
            entity.setUser(userRepository.findById(userId).get());
        }
        teacherRepository.save(entity);
    }

    @Override
    public TeacherServiceModel getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .map(e-> modelMapper.map(e,TeacherServiceModel.class))
                .orElseThrow();
    }

    @Override
    public boolean existByUserId(Long userId) {
        return teacherRepository.existsByUserId(userId);
    }

    @Override
    public TeacherServiceModel getTeacherByUsername(String username) {
        TeacherEntity entity = teacherRepository.findByUser_Username(username);
        if (entity == null){
            return null;
        }
        return modelMapper.map(entity,TeacherServiceModel.class);
    }

    @Override
    public void deleteTeacher(Long id) {
        subjectRepository.findAllByTeacherId(id)
                .stream()
                .map(subjectEntity -> subjectEntity.setTeacher(null))
                .forEach(subjectRepository::save);
        this.teacherRepository.deleteById(id);
    }
}
