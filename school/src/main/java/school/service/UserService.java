package school.service;

import school.model.service.UserAuthenticationServiceModel;
import school.model.service.UserServiceModel;

import java.util.List;

public interface UserService  {

    List<UserServiceModel> getAllUsers();

    void deleteUser(Long id);

    UserServiceModel getUser(Long id);

    UserAuthenticationServiceModel getUserWithAuthorities(Long userId);

    void addAuthority(Long userId,String authority);

    void removeAuthority(Long userId,String authority);

    List<UserServiceModel> getAllAdmins();

    List<UserServiceModel> getAllTeachers();

    List<UserServiceModel> getAllStudents();

    long getUserCount();

    boolean resendPassword(String email);
}
