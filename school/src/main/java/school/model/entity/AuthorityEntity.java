package school.model.entity;

import school.model.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class AuthorityEntity extends BaseEntity {

    private String authority;

    public AuthorityEntity() {
    }

    @Column(nullable = false,unique = true)
    public String getAuthority() {
        return authority;
    }

    public AuthorityEntity setAuthority(String authority) {
        this.authority = authority;
        return this;
    }
}
