package school.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teachers")
public class TeacherEntity extends BaseEntity{

    private String firstName;
    private String middleName;
    private String lastName;
    private UserEntity user;

    public TeacherEntity() {
    }

    public String getFirstName() {
        return firstName;
    }

    public TeacherEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public TeacherEntity setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public TeacherEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public TeacherEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
