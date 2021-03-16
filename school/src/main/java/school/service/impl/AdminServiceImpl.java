package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.constants.enumuration.AuthorityEnum;
import school.model.entity.AuthorityEntity;
import school.model.entity.UserEntity;
import school.model.service.UserServiceModel;
import school.repository.AuthorityRepository;
import school.repository.UserRepository;
import school.service.AdminService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl extends BaseService implements AdminService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public AdminServiceImpl(ModelMapper modelMapper, UserRepository userRepository, AuthorityRepository authorityRepository) {
        super(modelMapper);
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<UserServiceModel> getAllUsers() {
        List<UserEntity> all = this.userRepository.findAll();
        return this.userRepository.findAll()
                .stream()
                .map(u-> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserServiceModel getUser(Long id) {
        Optional<UserEntity> optionalUserEntity = this.userRepository.findById(id);
        return optionalUserEntity.map(e -> this.modelMapper.map(e, UserServiceModel.class)).orElseThrow();
    }

    @Override
    @Transactional
    public void addAuthority(Long userId,String authority) {
        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow();
        AuthorityEntity authorityEntity = this.authorityRepository.findByAuthority(authority)
                .orElseThrow();
        userEntity.getAuthorities().add(authorityEntity);
    }

    @Override
    @Transactional
    public void removeAuthority(Long userId,String authority) {
        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow();
        AuthorityEntity authorityEntity = this.authorityRepository.findByAuthority(authority)
                .orElseThrow();
        userEntity.getAuthorities().remove(authorityEntity);
    }

    @Override
    public List<UserServiceModel> getAllAdmins() {
        return this.userRepository.findAllByAuthority(AuthorityEnum.ADMIN.name())
                .stream()
                .map(entity-> this.modelMapper.map(entity,UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserServiceModel> getAllTeachers() {
        return this.userRepository.findAllByAuthority(AuthorityEnum.TEACHER.name())
                .stream()
                .map(entity-> this.modelMapper.map(entity,UserServiceModel.class))
                .collect(Collectors.toList());
    }
}
