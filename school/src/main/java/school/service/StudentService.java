package school.service;

import school.model.service.StudentServiceModel;

import java.util.List;

public interface StudentService {

    StudentServiceModel addStudent(StudentServiceModel serviceModel);

    List<StudentServiceModel> getStudentsByClassId(Long groupId);

    StudentServiceModel getStudentById(Long id);

    StudentServiceModel editStudent(StudentServiceModel serviceModel);

    boolean deleteStudent(Long id);

    StudentServiceModel getStudentByUserUsername(String username);

    boolean emailIsTheSame(StudentServiceModel serviceModel);

}
