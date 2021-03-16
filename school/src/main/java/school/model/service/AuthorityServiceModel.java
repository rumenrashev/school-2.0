package school.model.service;

import org.springframework.security.core.GrantedAuthority;
import school.model.base.BaseModel;

public class AuthorityServiceModel extends BaseModel implements GrantedAuthority {

    private String authority;

    public AuthorityServiceModel() {
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
