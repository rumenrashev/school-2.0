package school.model.service;

import org.springframework.security.core.userdetails.UserDetails;
import school.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel extends BaseModel implements UserDetails {

    private String username;
    private String password;
    private List<AuthorityServiceModel> authorities;

    public UserServiceModel() {
        this.authorities = new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public List<AuthorityServiceModel> getAuthorities() {
        return authorities;
    }

    public UserServiceModel setAuthorities(List<AuthorityServiceModel> authorities) {
        this.authorities = authorities;
        return this;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
