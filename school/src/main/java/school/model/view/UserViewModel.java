package school.model.view;

import school.model.BaseModel;

import java.util.List;

public class UserViewModel extends BaseModel {

    private String email;
    private List<String> authorities;

    public UserViewModel() {
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public UserViewModel setAuthorities(List<String> authorities) {
        this.authorities = authorities;
        return this;
    }
}
