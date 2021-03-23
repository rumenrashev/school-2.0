package school.model.service;

import school.model.BaseModel;

public class StudentServiceModel extends BaseModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private Long groupId;
    private Long userId;

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

    public Long getGroupId() {
        return groupId;
    }

    public StudentServiceModel setGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public StudentServiceModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
