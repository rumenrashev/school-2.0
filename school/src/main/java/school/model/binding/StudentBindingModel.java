package school.model.binding;

import org.hibernate.validator.constraints.Length;
import school.model.BaseModel;

import javax.validation.constraints.Pattern;

public class StudentBindingModel extends BaseModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private Long classroomId;
    private Long userId;
    private String userUsername;

    public StudentBindingModel() {
    }

    @Pattern(regexp = "^[А-Я][а-я]{2,19}$",message = "Името трябва да е мужду 3 и 12 букви и да започва с главна буква.")
    public String getFirstName() {
        return firstName;
    }

    public StudentBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Pattern(regexp = "^[А-Я][а-я]{2,19}$",message = "Презимето трябва да е мужду 3 и 12 букви и да започва с главна буква.")
    public String getMiddleName() {
        return middleName;
    }

    public StudentBindingModel setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    @Pattern(regexp = "^[А-Я][а-я]{2,19}$",message = "Фамилия трябва да е мужду 3 и 12 букви и да започва с главна буква.")
    public String getLastName() {
        return lastName;
    }

    public StudentBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public StudentBindingModel setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public StudentBindingModel setUserId(Long userId) {
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
