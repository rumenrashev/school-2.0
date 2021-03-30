package school.model.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "marks")
public class MarkEntity extends BaseEntity {

    private int value;
    private StudentEntity student;
    private SubjectEntity subject;

    public MarkEntity() {
    }

    public int getValue() {
        return value;
    }

    public MarkEntity setValue(int value) {
        this.value = value;
        return this;
    }

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    public StudentEntity getStudent() {
        return student;
    }

    public MarkEntity setStudent(StudentEntity student) {
        this.student = student;
        return this;
    }

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    public SubjectEntity getSubject() {
        return subject;
    }

    public MarkEntity setSubject(SubjectEntity subject) {
        this.subject = subject;
        return this;
    }
}
