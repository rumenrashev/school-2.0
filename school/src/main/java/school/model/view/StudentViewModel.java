package school.model.view;

import school.model.BaseModel;

public class StudentViewModel extends BaseModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private Long groupId;
    private Long userId;

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

    public Long getGroupId() {
        return groupId;
    }

    public StudentViewModel setGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public StudentViewModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
