package school.model.entity;

import school.model.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    private String password;
    private List<AuthorityEntity> authorities;

    public UserEntity() {
        this.authorities = new ArrayList<>();
    }

    @Column(nullable = false,unique = true)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public UserEntity setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
        return this;
    }
}
