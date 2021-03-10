package school.model.service;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityServiceModel implements GrantedAuthority {

    private Long id;
    private String authority;

    public AuthorityServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public AuthorityServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public AuthorityServiceModel setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    @Override
    public String toString() {
        return authority;
    }
}
