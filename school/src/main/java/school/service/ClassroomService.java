package school.service;

import school.model.service.ClassroomServiceModel;

import java.util.List;

public interface ClassroomService {

    boolean createClassroom(ClassroomServiceModel model);

    List<ClassroomServiceModel> getAll();

    ClassroomServiceModel getById(Long id);

    long getClassroomsCount();

}
