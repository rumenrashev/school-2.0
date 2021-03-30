package school.service;

import school.model.service.ClassroomServiceModel;
import school.model.service.StudentServiceModel;
import school.model.service.UserServiceModel;

import java.util.List;

public interface StudentService {

    StudentServiceModel addStudent(StudentServiceModel serviceModel);

    List<StudentServiceModel> getStudentsByClassId(Long groupId);

    StudentServiceModel getStudentById(Long id);

    StudentServiceModel editStudent(StudentServiceModel serviceModel);

    boolean deleteStudent(Long id);

    List<UserServiceModel> getAllFreeStudentUsers();

    StudentServiceModel getStudentByUserUsername(String username);

}
