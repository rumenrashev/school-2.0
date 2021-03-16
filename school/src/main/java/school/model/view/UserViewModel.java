package school.model.view;

import school.model.BaseModel;

import java.util.List;

public class UserViewModel extends BaseModel {

    private String username;
    private List<String> authorities;

    public UserViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
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
