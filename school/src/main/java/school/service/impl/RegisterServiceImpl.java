package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import school.constants.enumuration.AuthorityEnum;
import school.model.entity.UserEntity;
import school.model.service.UserServiceModel;
import school.repository.AuthorityRepository;
import school.repository.UserRepository;
import school.service.RegisterService;
import school.service.base.BaseService;

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
    public void registerUser(UserServiceModel userServiceModel) {
        userServiceModel.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));
        UserEntity userEntity = this.modelMapper.map(userServiceModel, UserEntity.class);
        if (this.userRepository.count() == 0){
            userEntity.getAuthorities().add(this.authorityRepository.findByAuthority(AuthorityEnum.ADMIN.name()).orElseThrow());
        }
        userEntity.getAuthorities().add(this.authorityRepository.findByAuthority(AuthorityEnum.USER.name()).orElseThrow());
        this.userRepository.saveAndFlush(userEntity);
    }
}
