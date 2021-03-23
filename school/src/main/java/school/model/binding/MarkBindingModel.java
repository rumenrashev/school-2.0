package school.model.binding;

import school.model.BaseModel;

public class MarkBindingModel extends BaseModel {

    private int value;
    private Long studentId;
    private Long subjectId;

    public MarkBindingModel() {
    }

    public int getValue() {
        return value;
    }

    public MarkBindingModel setValue(int value) {
        this.value = value;
        return this;
    }

    public Long getStudentId() {
        return studentId;
    }

    public MarkBindingModel setStudentId(Long studentId) {
        this.studentId = studentId;
        return this;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public MarkBindingModel setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
        return this;
    }
}
