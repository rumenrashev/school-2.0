package school.model.view;

import school.constants.enumuration.SubjectEnum;
import school.model.BaseModel;

public class SubjectViewModel extends BaseModel {

    private SubjectEnum subject;
    private Long GroupId;

    public SubjectViewModel() {
    }

    public SubjectEnum getSubject() {
        return subject;
    }

    public SubjectViewModel setSubject(SubjectEnum subject) {
        this.subject = subject;
        return this;
    }

    public Long getGroupId() {
        return GroupId;
    }

    public SubjectViewModel setGroupId(Long groupId) {
        GroupId = groupId;
        return this;
    }
}
