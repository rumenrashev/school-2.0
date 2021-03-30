package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import school.constants.enumuration.AuthorityEnum;
import school.exception.AuthorityNotFoundException;
import school.exception.UserIdNotFountException;
import school.model.entity.AuthorityEntity;
import school.model.entity.TeacherEntity;
import school.model.entity.UserEntity;
import school.model.service.UserServiceModel;
import school.repository.AuthorityRepository;
import school.repository.TeacherRepository;
import school.repository.UserRepository;
import school.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final TeacherRepository teacherRepository;

    public UserServiceImpl(ModelMapper modelMapper,
                           UserRepository userRepository,
                           AuthorityRepository authorityRepository,
                           TeacherRepository teacherRepository) {
        super(modelMapper);
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.teacherRepository = teacherRepository;
    }


    @Override
    public List<UserServiceModel> getAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        Optional<TeacherEntity> teacher = teacherRepository.findByUserId(id);
        teacher.ifPresent(teacherEntity -> teacherRepository.save(teacherEntity.setUser(null)));
        this.userRepository.deleteById(id);
    }

    @Override
    public UserServiceModel getUser(Long id) {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findById(id);
        return optionalUserEntity.map(e -> this.modelMapper.map(e, UserServiceModel.class)).orElseThrow();
    }

    @Override
    @Transactional
    public void addAuthority(Long userId, String authority) {
        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(UserIdNotFountException::new);
        AuthorityEntity authorityEntity = this.authorityRepository.findByAuthority(authority)
                .orElseThrow(AuthorityNotFoundException::new);
        userEntity.getAuthorities().add(authorityEntity);
    }

    @Override
    @Transactional
    public void removeAuthority(Long userId, String authority) {
        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(UserIdNotFountException::new);
        AuthorityEntity authorityEntity = this.authorityRepository.findByAuthority(authority)
                .orElseThrow(AuthorityNotFoundException::new);
        userEntity.getAuthorities().remove(authorityEntity);
    }

    @Override
    public List<UserServiceModel> getAllAdmins() {
        return this.userRepository.findAllByAuthority(AuthorityEnum.ADMIN.name())
                .stream()
                .map(entity -> this.modelMapper.map(entity, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserServiceModel> getAllTeachers() {
        return this.userRepository.findAllByAuthority(AuthorityEnum.TEACHER.name())
                .stream()
                .map(entity -> this.modelMapper.map(entity, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserServiceModel> getAllStudents() {
        return this.userRepository.findAllByAuthority(AuthorityEnum.STUDENT.name())
                .stream()
                .map(entity -> this.modelMapper.map(entity, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public long getUserCount() {
        return userRepository.count();
    }
}
