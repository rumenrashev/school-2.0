package school.model.service;

import school.model.BaseModel;

import java.util.List;

public class StudentServiceModel extends BaseModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private ClassroomServiceModel classroom;
    private UserServiceModel user;
    private List<MarkServiceModel> marks;

    public StudentServiceModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public StudentServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public StudentServiceModel setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ClassroomServiceModel getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomServiceModel classroom) {
        this.classroom = classroom;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public List<MarkServiceModel> getMarks() {
        return marks;
    }

    public void setMarks(List<MarkServiceModel> marks) {
        this.marks = marks;
    }
}
