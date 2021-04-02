package school.model.entity;

import school.constants.enumuration.ClassroomLetter;
import school.constants.enumuration.ClassroomNumber;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classrooms")
public class ClassroomEntity extends BaseEntity {

    private ClassroomNumber number;
    private ClassroomLetter letter;
    private List<SubjectEntity> subjects;
    private List<StudentEntity> students;

    @Enumerated(EnumType.ORDINAL)
    public ClassroomNumber getNumber() {
        return number;
    }

    public ClassroomEntity setNumber(ClassroomNumber number) {
        this.number = number;
        return this;
    }

    @Enumerated(EnumType.ORDINAL)
    public ClassroomLetter getLetter() {
        return letter;
    }

    public ClassroomEntity setLetter(ClassroomLetter letter) {
        this.letter = letter;
        return this;
    }

    @OneToMany(mappedBy = "classroom")
    public List<SubjectEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectEntity> subjects) {
        this.subjects = subjects;
    }

    @OneToMany(mappedBy = "classroom")
    public List<StudentEntity> getStudents() {
        return students;
    }

    public ClassroomEntity setStudents(List<StudentEntity> students) {
        this.students = students;
        return this;
    }
}
