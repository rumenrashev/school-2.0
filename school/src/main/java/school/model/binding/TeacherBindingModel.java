package school.model.binding;

import school.model.BaseModel;

public class TeacherBindingModel extends BaseModel {

    private String firstName;
    private String middleName;
    private String lastName;

    public TeacherBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public TeacherBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public TeacherBindingModel setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public TeacherBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
