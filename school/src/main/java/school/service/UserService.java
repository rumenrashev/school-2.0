package school.service;

import school.model.service.UserServiceModel;

import java.util.List;

public interface UserService  {


    List<UserServiceModel> getAllUsers();

    void deleteUser(Long id);

    UserServiceModel getUser(Long id);

    void addAuthority(Long userId,String authority);

    void removeAuthority(Long userId,String authority);

    List<UserServiceModel> getAllAdmins();

    List<UserServiceModel> getAllTeachers();

    List<UserServiceModel> getAllStudents();


}
