package school.model.view;

import school.constants.enumuration.SubjectEnum;
import school.model.BaseModel;

public class SubjectViewModel extends BaseModel {

    private SubjectEnum subject;
    private GroupViewModel groupViewModel;
    private TeacherViewModel teacher;

    public SubjectViewModel() {
    }

    public SubjectEnum getSubject() {
        return subject;
    }

    public SubjectViewModel setSubject(SubjectEnum subject) {
        this.subject = subject;
        return this;
    }

    public GroupViewModel getGroupViewModel() {
        return groupViewModel;
    }

    public SubjectViewModel setGroupViewModel(GroupViewModel groupViewModel) {
        this.groupViewModel = groupViewModel;
        return this;
    }

    public TeacherViewModel getTeacher() {
        return teacher;
    }

    public SubjectViewModel setTeacher(TeacherViewModel teacher) {
        this.teacher = teacher;
        return this;
    }
}
