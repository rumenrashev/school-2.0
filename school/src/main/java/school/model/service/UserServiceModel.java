package school.model.service;

import org.springframework.security.core.userdetails.UserDetails;
import school.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel extends BaseModel {

    private String email;

    public UserServiceModel() {
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
