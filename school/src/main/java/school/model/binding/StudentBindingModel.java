package school.model.binding;

public class StudentBindingModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private Long groupId;

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
}
