package school.service;

import school.model.service.StudentServiceModel;

import java.util.List;

public interface StudentService {

    void addStudent(StudentServiceModel serviceModel);

    List<StudentServiceModel> getStudentsByClassId(Long groupId);

}
