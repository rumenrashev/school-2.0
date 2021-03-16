package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.model.service.UserServiceModel;
import school.repository.UserRepository;
import school.service.LoginService;

@Service
public class LoginServiceImpl extends BaseService implements LoginService {

    private final UserRepository userRepository;

    public LoginServiceImpl(ModelMapper modelMapper, UserRepository userRepository1) {
        super(modelMapper);
        this.userRepository = userRepository1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .map(entity-> this.modelMapper.map(entity, UserServiceModel.class))
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
