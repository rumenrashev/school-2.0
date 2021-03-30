package school.model.entity;

import school.constants.enumuration.GroupLetter;
import school.constants.enumuration.GroupNumber;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classrooms")
public class ClassroomEntity extends BaseEntity {

    private GroupNumber number;
    private GroupLetter letter;
    private List<SubjectEntity> subjects;
    private List<StudentEntity> students;

    @Enumerated(EnumType.ORDINAL)
    public GroupNumber getNumber() {
        return number;
    }

    public ClassroomEntity setNumber(GroupNumber number) {
        this.number = number;
        return this;
    }

    @Enumerated(EnumType.ORDINAL)
    public GroupLetter getLetter() {
        return letter;
    }

    public ClassroomEntity setLetter(GroupLetter letter) {
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
