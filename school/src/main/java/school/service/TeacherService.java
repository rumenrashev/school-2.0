package school.service;

import school.model.service.StudentServiceModel;
import school.model.service.TeacherServiceModel;

import java.util.List;

public interface TeacherService {

    void addTeacher(TeacherServiceModel serviceModel);

    List<TeacherServiceModel> getAllTeachers();

    void editTeacher(TeacherServiceModel serviceModel);

    TeacherServiceModel getTeacherById(Long id);

    boolean existByUserId(Long userId);

    TeacherServiceModel getTeacherByUsername(String username);

    void deleteTeacher(Long id);
}
