package school.model.view;

import java.util.List;

public class UserViewModel {

    private Long id;
    private String username;
    private List<String> authorities;

    public UserViewModel() {
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
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
