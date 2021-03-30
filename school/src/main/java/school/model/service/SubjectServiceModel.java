package school.model.service;

import school.constants.enumuration.SubjectEnum;
import school.model.BaseModel;

public class SubjectServiceModel extends BaseModel {

    private SubjectEnum subject;
    private ClassroomServiceModel classroom;
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


    public ClassroomServiceModel getClassroom() {
        return classroom;
    }

    public SubjectServiceModel setClassroom(ClassroomServiceModel classroom) {
        this.classroom = classroom;
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
