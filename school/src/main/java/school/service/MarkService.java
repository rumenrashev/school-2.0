package school.service;

import school.model.service.MarkServiceModel;

import java.util.List;

public interface MarkService {

    void addMark(MarkServiceModel serviceModel);

    List<MarkServiceModel> getMarksByStudentAndSubject(Long subjectId,Long studentId);


    void deleteMark(Long markId);

    void editMark(MarkServiceModel serviceModel);

    Double getStudentAverageMark(List<MarkServiceModel> marks);

}
