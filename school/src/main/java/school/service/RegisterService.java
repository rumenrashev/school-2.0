package school.service;

import school.model.service.UserServiceModel;

public interface RegisterService {


    UserServiceModel registerStudent(String username);

    UserServiceModel registerTeacher(String username);

    boolean isValid(String email);


}
