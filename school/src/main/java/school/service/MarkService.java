package school.service;

import school.model.service.MarkServiceModel;

import java.util.List;

public interface MarkService {

    MarkServiceModel addMark(MarkServiceModel serviceModel);

    List<MarkServiceModel> getMarksByStudentAndSubject(Long subjectId,Long studentId);

    boolean deleteMark(Long markId);

    MarkServiceModel editMark(MarkServiceModel serviceModel);

    Double getStudentAverageMark(List<MarkServiceModel> marks);

    long getMarksCount();


}
