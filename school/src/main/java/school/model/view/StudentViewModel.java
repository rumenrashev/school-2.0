package school.model.view;

import school.model.BaseModel;

public class StudentViewModel extends BaseModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private Long classroomId;
    private Long userId;
    private String userUsername;

    public StudentViewModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public StudentViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public StudentViewModel setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public StudentViewModel setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public StudentViewModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
}
