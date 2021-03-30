package school.model.view;

import school.constants.enumuration.SubjectEnum;
import school.model.BaseModel;

public class SubjectViewModel extends BaseModel {

    private SubjectEnum subject;
    private ClassroomViewModel classroom;
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

    public ClassroomViewModel getClassroom() {
        return classroom;
    }

    public SubjectViewModel setClassroom(ClassroomViewModel classroom) {
        this.classroom = classroom;
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
