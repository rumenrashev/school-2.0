package school.model.entity;

import school.constants.enumuration.SubjectEnum;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")
public class SubjectEntity extends BaseEntity {

    private SubjectEnum subject;
    private GroupEntity group;

    public SubjectEntity() {
    }

    public SubjectEnum getSubject() {
        return subject;
    }

    public SubjectEntity setSubject(SubjectEnum subject) {
        this.subject = subject;
        return this;
    }

    @ManyToOne
    public GroupEntity getGroup() {
        return group;
    }

    public SubjectEntity setGroup(GroupEntity group) {
        this.group = group;
        return this;
    }
}
