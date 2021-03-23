package school.model.service;

import school.constants.enumuration.SubjectEnum;
import school.model.BaseModel;

public class SubjectServiceModel extends BaseModel {

    private SubjectEnum subject;
    private GroupServiceModel group;
    private TeacherServiceModel teacher;

    public SubjectServiceModel() {
    }

    public SubjectEnum getSubject() {
        return subject;
    }

    public SubjectServiceModel setSubject(SubjectEnum subject) {
        this.subject = subject;
        return this;
    }


    public GroupServiceModel getGroup() {
        return group;
    }

    public SubjectServiceModel setGroup(GroupServiceModel group) {
        this.group = group;
        return this;
    }

    public TeacherServiceModel getTeacher() {
        return teacher;
    }

    public SubjectServiceModel setTeacher(TeacherServiceModel teacher) {
        this.teacher = teacher;
        return this;
    }
}
