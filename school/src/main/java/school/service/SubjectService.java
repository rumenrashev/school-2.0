package school.service;

import school.constants.enumuration.SubjectEnum;
import school.model.service.SubjectServiceModel;

import java.util.List;

public interface SubjectService {

    void addSubject(SubjectServiceModel serviceModel);

    List<SubjectServiceModel> getAllSubjectsByClassId(Long id);

    void deleteSubject(Long id);

    boolean subjectExists(SubjectEnum subject,Long groupId);

    SubjectServiceModel getSubjectById(Long id);
}
