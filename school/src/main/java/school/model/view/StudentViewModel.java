package school.model.view;

import school.model.BaseModel;

public class StudentViewModel extends BaseModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private Long groupId;

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
}
