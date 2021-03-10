package school.service;

import school.model.service.UserServiceModel;

import java.util.Collection;
import java.util.List;

public interface AdminService {

    List<UserServiceModel> getAllUsers();

    void deleteUser(Long id);

    UserServiceModel getUser(Long id);

    void addAuthority(Long user_id,String authority);

    void removeAuthority(Long userId,String authority);

    List<UserServiceModel> getAllAdmins();

}
