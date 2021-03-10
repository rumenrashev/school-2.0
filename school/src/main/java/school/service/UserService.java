package school.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import school.model.service.UserServiceModel;

public interface UserService extends UserDetailsService {

    void registerUser(UserServiceModel userServiceModel);
}
