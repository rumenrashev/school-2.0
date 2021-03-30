package school.service;

import school.constants.enumuration.SubjectEnum;
import school.model.service.SubjectServiceModel;
import school.model.service.TeacherServiceModel;

import java.util.List;

public interface SubjectService {

    SubjectServiceModel addSubject(SubjectServiceModel serviceModel);

    List<SubjectServiceModel> getAllSubjectsByClassId(Long id);

    boolean deleteSubject(Long id);

    boolean subjectExists(SubjectEnum subject, Long groupId);

    SubjectServiceModel getSubjectById(Long id);

    SubjectServiceModel editSubject(SubjectServiceModel serviceModel);

}
