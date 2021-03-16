package school.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class StudentEntity extends BaseEntity {

    private String firstName;
    private String middleName;
    private String lastName;
    private GroupEntity group;

    public StudentEntity() {
    }

    public String getFirstName() {
        return firstName;
    }

    public StudentEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public StudentEntity setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @ManyToOne
    public GroupEntity getGroup() {
        return group;
    }

    public StudentEntity setGroup(GroupEntity group) {
        this.group = group;
        return this;
    }
}
