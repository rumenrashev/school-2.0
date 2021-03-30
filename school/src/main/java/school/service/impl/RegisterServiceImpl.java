package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import school.constants.enumuration.AuthorityEnum;
import school.exception.AuthorityNotFoundException;
import school.model.entity.UserEntity;
import school.model.service.UserAuthenticationServiceModel;
import school.repository.AuthorityRepository;
import school.repository.UserRepository;
import school.service.RegisterService;

@Service
public class RegisterServiceImpl extends BaseService implements RegisterService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RegisterServiceImpl(ModelMapper modelMapper,
                               UserRepository userRepository,
                               AuthorityRepository authorityRepository,
                               BCryptPasswordEncoder passwordEncoder) {
        super(modelMapper);
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAuthenticationServiceModel registerUser(UserAuthenticationServiceModel userServiceModel) {
        UserEntity userEntity = this.modelMapper.map(userServiceModel, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.getAuthorities().add(
                this.authorityRepository
                        .findByAuthority(AuthorityEnum.USER.name())
                        .orElseThrow(AuthorityNotFoundException::new));
        userEntity = this.userRepository.saveAndFlush(userEntity);
        return modelMapper.map(userEntity,UserAuthenticationServiceModel.class);
    }

}
