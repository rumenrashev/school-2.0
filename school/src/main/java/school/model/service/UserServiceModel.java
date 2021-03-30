package school.model.service;

import org.springframework.security.core.userdetails.UserDetails;
import school.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel extends BaseModel {

    private String username;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
