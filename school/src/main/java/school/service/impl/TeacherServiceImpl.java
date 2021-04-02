package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.constants.enumuration.AuthorityEnum;
import school.exception.TeacherNotFoundException;
import school.model.entity.TeacherEntity;
import school.model.service.TeacherServiceModel;
import school.model.service.UserServiceModel;
import school.repository.TeacherRepository;
import school.repository.UserRepository;
import school.service.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl extends BaseService implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    @Autowired
    public TeacherServiceImpl(ModelMapper modelMapper,
                              TeacherRepository teacherRepository,
                              UserRepository userRepository) {
        super(modelMapper);
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TeacherServiceModel addTeacher(TeacherServiceModel serviceModel) {
        TeacherEntity teacherEntity = modelMapper.map(serviceModel, TeacherEntity.class);
        TeacherEntity saved = teacherRepository.saveAndFlush(teacherEntity);
        return modelMapper.map(saved, TeacherServiceModel.class);
    }

    @Override
    public List<TeacherServiceModel> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, TeacherServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public TeacherServiceModel editTeacher(TeacherServiceModel serviceModel) {
        return addTeacher(serviceModel);
    }

    @Override
    public TeacherServiceModel getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .map(e -> modelMapper.map(e, TeacherServiceModel.class))
                .orElseThrow(TeacherNotFoundException::new);
    }

    @Override
    public boolean existByUserId(Long userId) {
        return teacherRepository.existsByUserId(userId);
    }

    @Override
    public TeacherServiceModel getTeacherByUsername(String username) {
        TeacherEntity entity = teacherRepository.findByUserEmail(username);
        if (entity == null) {
            return null;
        }
        return modelMapper.map(entity, TeacherServiceModel.class);
    }

    @Override
    public void deleteTeacher(Long id) {
        TeacherEntity teacher = teacherRepository
                .findById(id)
                .orElseThrow(TeacherNotFoundException::new);
        teacher.getSubjects().forEach(s -> s.setTeacher(null));
        teacherRepository.deleteById(id);
        userRepository.deleteById(teacher.getUser().getId());
    }

    @Override
    public long getTeachersCount() {
        return teacherRepository.count();
    }

    @Override
    public List<UserServiceModel> getAllFreeTeachersUsers() {
        return userRepository.findAllByAuthority(AuthorityEnum.TEACHER.name())
                .stream()
                .filter(user -> !teacherRepository.existsByUserId(user.getId()))
                .map(entity -> modelMapper.map(entity, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean emailIsSame(TeacherServiceModel teacherServiceModel) {
        UserServiceModel userServiceModel =
                getTeacherById(teacherServiceModel.getId()).getUser();
        boolean emailIsSame = teacherRepository.existsByIdAndUserEmail(
                teacherServiceModel.getId(), teacherServiceModel.getUser().getEmail());
        if (!emailIsSame && !userRepository.existsByEmail(teacherServiceModel.getUser().getEmail())) {
            TeacherEntity teacherEntity =
                    modelMapper.map(teacherServiceModel, TeacherEntity.class);
            teacherEntity.setUser(null);
            teacherRepository.save(teacherEntity);
            userRepository.deleteById(userServiceModel.getId());
        }
        return emailIsSame;
    }
}
