package school.service.impl;

import org.modelmapper.ModelMapper;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import school.constants.enumuration.AuthorityEnum;
import school.exception.AuthorityNotFoundException;
import school.exception.UserIdNotFountException;
import school.model.entity.AuthorityEntity;
import school.model.entity.TeacherEntity;
import school.model.entity.UserEntity;
import school.model.service.UserAuthenticationServiceModel;
import school.model.service.UserServiceModel;
import school.repository.AuthorityRepository;
import school.repository.TeacherRepository;
import school.repository.UserRepository;
import school.assync.EmailSender;
import school.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static school.constants.GlobalConstants.*;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordGenerator passwordGenerator;
    private final EmailSender emailSender;

    public UserServiceImpl(ModelMapper modelMapper,
                           UserRepository userRepository,
                           AuthorityRepository authorityRepository,
                           TeacherRepository teacherRepository,
                           PasswordEncoder passwordEncoder,
                           PasswordGenerator passwordGenerator,
                           EmailSender emailSender) {
        super(modelMapper);
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordGenerator = passwordGenerator;
        this.emailSender = emailSender;
    }

    @Override
    public List<UserServiceModel> getAllUsers() {
        return this.userRepository.findAllByAuthority(AuthorityEnum.USER.name())
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
        return optionalUserEntity.map(e -> this.modelMapper.map(e, UserServiceModel.class))
                .orElseThrow();
    }

    @Override
    public UserAuthenticationServiceModel getUserWithAuthorities(Long userId) {
        return userRepository.findById(userId)
                .map(e-> modelMapper.map(e,UserAuthenticationServiceModel.class))
                .orElseThrow();
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

    @Override
    public boolean resendPassword(String email) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);
        if (optionalUserEntity.isPresent()){
            CharacterRule digit = new CharacterRule(EnglishCharacterData.Digit);
            CharacterRule lowerCase = new CharacterRule(EnglishCharacterData.LowerCase);
            CharacterRule upperCase = new CharacterRule(EnglishCharacterData.UpperCase);
            String newPassword = passwordGenerator.generatePassword(8,digit,lowerCase,upperCase);
            UserEntity userEntity = optionalUserEntity.get();
            userEntity.setPassword(passwordEncoder.encode(newPassword));
            userRepository.saveAndFlush(userEntity);
            emailSender.sendMail(email,
                    FORGOTTEN_PASSWORD_SUBJECT,
                    String.format(EMAIL_BODY,email,newPassword));

            return true;
        }
        return false;
    }
}
