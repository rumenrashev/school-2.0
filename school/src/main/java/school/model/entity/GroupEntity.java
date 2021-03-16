package school.model.entity;

import school.constants.enumuration.GroupLetter;
import school.constants.enumuration.GroupNumber;
import school.model.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student_gropus")
public class GroupEntity extends BaseEntity {

    private GroupNumber number;
    private GroupLetter letter;
    private List<StudentEntity> students;

    @Enumerated(EnumType.ORDINAL)
    public GroupNumber getNumber() {
        return number;
    }

    public GroupEntity setNumber(GroupNumber number) {
        this.number = number;
        return this;
    }

    @Enumerated(EnumType.ORDINAL)
    public GroupLetter getLetter() {
        return letter;
    }

    public GroupEntity setLetter(GroupLetter letter) {
        this.letter = letter;
        return this;
    }

    @OneToMany(mappedBy = "group")
    public List<StudentEntity> getStudents() {
        return students;
    }

    public GroupEntity setStudents(List<StudentEntity> students) {
        this.students = students;
        return this;
    }
}
