package school.service.impl;

import org.modelmapper.ModelMapper;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import school.constants.enumuration.AuthorityEnum;
import school.exception.AuthorityNotFoundException;
import school.model.entity.AuthorityEntity;
import school.model.entity.UserEntity;
import school.model.service.UserAuthenticationServiceModel;
import school.model.service.UserServiceModel;
import school.repository.AuthorityRepository;
import school.repository.UserRepository;
import school.assync.EmailSender;
import school.service.RegisterService;

import java.util.List;
import java.util.Optional;

import static school.constants.enumuration.AuthorityEnum.*;
import static school.constants.GlobalConstants.*;

@Service
public class RegisterServiceImpl extends BaseService implements RegisterService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailSender emailSender;
    private final PasswordGenerator passwordGenerator;

    @Autowired
    public RegisterServiceImpl(ModelMapper modelMapper,
                               UserRepository userRepository,
                               AuthorityRepository authorityRepository,
                               BCryptPasswordEncoder passwordEncoder,
                               EmailSender emailSender, PasswordGenerator passwordGenerator) {
        super(modelMapper);
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender = emailSender;
        this.passwordGenerator = passwordGenerator;
    }

    @Override
    public UserServiceModel registerStudent(String email) {
        UserEntity user = createUser(email);
        if (user.getAuthorities().isEmpty()) {
            user.setAuthorities(List.of(getAuthority(STUDENT), getAuthority(USER)));
        }
        return modelMapper.map(userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel registerTeacher(String email) {
        UserEntity user = createUser(email);
        if (user.getAuthorities().isEmpty()) {
            user.setAuthorities(List.of(getAuthority(TEACHER), getAuthority(USER)));
        }
        return modelMapper.map(userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public boolean isValid(String email) {
        return !userRepository.existsByEmail(email);
    }

    private UserEntity createUser(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        String password = generatePassword();
        UserEntity userEntity = new UserEntity()
                .setEmail(email)
                .setPassword(passwordEncoder.encode(password));
        emailSender.sendMail(
                email,
                ACCOUNT_CREATED_SUBJECT,
                String.format(EMAIL_BODY,email,password));
        return userEntity;
    }

    private AuthorityEntity getAuthority(AuthorityEnum authorityEnum) {
        return authorityRepository.findByAuthority(authorityEnum.name())
                .orElseThrow(AuthorityNotFoundException::new);
    }

    private String generatePassword(){
        CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);
        CharacterRule lowerCase = new CharacterRule(EnglishCharacterData.LowerCase);
        CharacterRule upperCase = new CharacterRule(EnglishCharacterData.UpperCase);
        return passwordGenerator.generatePassword(10, digits,lowerCase,upperCase);
    }
}
