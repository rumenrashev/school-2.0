package school.model.service;

import org.springframework.security.core.userdetails.UserDetails;
import school.model.BaseModel;

import java.util.List;

public class UserAuthenticationServiceModel extends BaseModel implements UserDetails {

    private String username;
    private String password;
    private List<AuthorityServiceModel> authorities;

    @Override
    public String getUsername() {
        return username;
    }

    public UserAuthenticationServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public UserAuthenticationServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public List<AuthorityServiceModel> getAuthorities() {
        return authorities;
    }

    public UserAuthenticationServiceModel setAuthorities(List<AuthorityServiceModel> authorities) {
        this.authorities = authorities;
        return this;
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
}
