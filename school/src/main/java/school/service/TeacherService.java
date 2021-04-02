package school.service;

import school.model.service.TeacherServiceModel;
import school.model.service.UserServiceModel;

import java.util.List;

public interface TeacherService {

    TeacherServiceModel addTeacher(TeacherServiceModel serviceModel);

    List<TeacherServiceModel> getAllTeachers();

    TeacherServiceModel editTeacher(TeacherServiceModel serviceModel);

    TeacherServiceModel getTeacherById(Long id);

    boolean existByUserId(Long userId);

    TeacherServiceModel getTeacherByUsername(String username);

    void deleteTeacher(Long id);

    long getTeachersCount();

    List<UserServiceModel> getAllFreeTeachersUsers();

    boolean emailIsSame(TeacherServiceModel teacherServiceModel);

}
