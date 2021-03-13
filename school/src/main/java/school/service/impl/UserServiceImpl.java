package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import school.enumuration.AuthorityEnum;
import school.model.entity.AuthorityEntity;
import school.model.entity.UserEntity;
import school.model.service.UserServiceModel;
import school.repository.AuthorityRepository;
import school.repository.UserRepository;
import school.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(ModelMapper modelMapper,
                           BCryptPasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           AuthorityRepository authorityRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
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

    @Override
    public void seedTestUsers() {
        if (userRepository.count() > 0){
            return;
        }
        AuthorityEntity adminRole = this.authorityRepository.findByAuthority(AuthorityEnum.ADMIN.name()).get();
        AuthorityEntity teacherRole = this.authorityRepository.findByAuthority(AuthorityEnum.TEACHER.name()).get();
        AuthorityEntity userRole = this.authorityRepository.findByAuthority(AuthorityEnum.USER.name()).get();
        seedAdmin(List.of(adminRole,teacherRole,userRole));
        seedTeacher(List.of(teacherRole,userRole));
        seedUser(List.of(userRole));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .map(entity-> this.modelMapper.map(entity,UserServiceModel.class))
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    private void seedAdmin(List<AuthorityEntity> authorities){
        UserEntity userEntity = new UserEntity()
                .setUsername("admin")
                .setPassword(this.passwordEncoder.encode("admin"))
                .setAuthorities(authorities);
        this.userRepository.saveAndFlush(userEntity);
    }

    private void seedTeacher(List<AuthorityEntity> authorities){
        UserEntity userEntity = new UserEntity()
                .setUsername("teacher")
                .setPassword(this.passwordEncoder.encode("teacher"))
                .setAuthorities(authorities);
        this.userRepository.saveAndFlush(userEntity);
    }

    private void seedUser(List<AuthorityEntity> authorities){
        UserEntity userEntity = new UserEntity()
                .setUsername("user")
                .setPassword(this.passwordEncoder.encode("user"))
                .setAuthorities(authorities);
        this.userRepository.saveAndFlush(userEntity);
    }
}
