package school.model.binding;

import org.hibernate.validator.constraints.Length;
import school.anotation.FieldMatch;
import school.anotation.UniqueUsername;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Does not match password."
)
public class UserRegisterBindingModel {

    private String username;
    private String password;
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    @Length(min = 3,max = 20,message = "Username must be between 3 and 20 characters")
    @UniqueUsername
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @Length(min = 3,max = 20,message = "Password must be between 3 and 20 characters")
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
