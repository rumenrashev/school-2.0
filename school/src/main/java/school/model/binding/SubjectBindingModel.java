package school.model.binding;

import school.constants.enumuration.SubjectEnum;

public class SubjectBindingModel {

    private SubjectEnum subject;
    private Long groupId;

    public SubjectBindingModel() {
    }

    public SubjectEnum getSubject() {
        return subject;
    }

    public SubjectBindingModel setSubject(SubjectEnum subject) {
        this.subject = subject;
        return this;
    }

    public Long getGroupId() {
        return groupId;
    }

    public SubjectBindingModel setGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }
}
