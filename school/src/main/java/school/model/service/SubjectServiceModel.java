package school.model.service;

import school.constants.enumuration.SubjectEnum;
import school.model.BaseModel;

public class SubjectServiceModel extends BaseModel {

    private SubjectEnum subject;
    private Long GroupId;
    private Long teacherId;

    public SubjectServiceModel() {
    }

    public SubjectEnum getSubject() {
        return subject;
    }

    public SubjectServiceModel setSubject(SubjectEnum subject) {
        this.subject = subject;
        return this;
    }

    public Long getGroupId() {
        return GroupId;
    }

    public SubjectServiceModel setGroupId(Long groupId) {
        GroupId = groupId;
        return this;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public SubjectServiceModel setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
        return this;
    }
}
