package school.model.entity;

import school.constants.enumuration.SubjectEnum;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")
public class SubjectEntity extends BaseEntity {

    private SubjectEnum subject;
    private ClassroomEntity classroom;
    private TeacherEntity teacher;

    public SubjectEntity() {
    }

    public SubjectEnum getSubject() {
        return subject;
    }

    public SubjectEntity setSubject(SubjectEnum subject) {
        this.subject = subject;
        return this;
    }


    @ManyToOne
    public ClassroomEntity getClassroom() {
        return classroom;
    }

    public SubjectEntity setClassroom(ClassroomEntity classroom) {
        this.classroom = classroom;
        return this;
    }

    @ManyToOne()
    public TeacherEntity getTeacher() {
        return teacher;
    }

    public SubjectEntity setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
        return this;
    }
}
