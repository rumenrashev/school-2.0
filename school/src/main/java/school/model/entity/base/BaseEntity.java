package school.model.entity.base;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    private Long id;

    protected BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,updatable = false,unique = true)
    public Long getId() {
        return id;
    }

    public BaseEntity setId(Long id) {
        this.id = id;
        return this;
    }
}
