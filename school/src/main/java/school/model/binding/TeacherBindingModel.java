package school.model.binding;

import school.model.BaseModel;

import javax.validation.constraints.Pattern;

public class TeacherBindingModel extends BaseModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private String userUsername;

    public TeacherBindingModel() {
    }

    @Pattern(regexp = "^[А-Я][а-я]{2,19}$",message = "Името трябва да е мужду 3 и 12 букви и да започва с главна буква.")
    public String getFirstName() {
        return firstName;
    }

    public TeacherBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Pattern(regexp = "^[А-Я][а-я]{2,19}$",message = "Презимето трябва да е мужду 3 и 12 букви и да започва с главна буква.")
    public String getMiddleName() {
        return middleName;
    }

    public TeacherBindingModel setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    @Pattern(regexp = "^[А-Я][а-я]{2,19}$",message = "Фамилията трябва да е мужду 3 и 12 букви и да започва с главна буква.")
    public String getLastName() {
        return lastName;
    }

    public TeacherBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
}
