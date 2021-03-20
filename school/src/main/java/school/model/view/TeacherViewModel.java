package school.model.view;

import school.model.BaseModel;

public class TeacherViewModel extends BaseModel {

    private String firstName;
    private String middleName;
    private String lastName;

    public TeacherViewModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public TeacherViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public TeacherViewModel setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public TeacherViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
