package school.model.binding;

import school.constants.enumuration.SubjectEnum;
import school.model.BaseModel;

public class SubjectBindingModel extends BaseModel {

    private SubjectEnum subject;
    private Long classroomId;
    private Long teacherId;

    public SubjectBindingModel() {
    }

    public SubjectEnum getSubject() {
        return subject;
    }

    public SubjectBindingModel setSubject(SubjectEnum subject) {
        this.subject = subject;
        return this;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public SubjectBindingModel setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
        return this;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public SubjectBindingModel setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
        return this;
    }
}
