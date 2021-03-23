package school.model.binding;

import school.model.BaseModel;

public class StudentBindingModel extends BaseModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private Long groupId;
    private Long userId;

    public StudentBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public StudentBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public StudentBindingModel setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getGroupId() {
        return groupId;
    }

    public StudentBindingModel setGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public StudentBindingModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
