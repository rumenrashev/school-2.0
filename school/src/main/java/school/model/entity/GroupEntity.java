package school.model.entity;

import school.constants.enumuration.GroupLetter;
import school.constants.enumuration.GroupNumber;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes")
public class GroupEntity extends BaseEntity {

    private GroupNumber number;
    private GroupLetter letter;

    @Enumerated(EnumType.ORDINAL)
    public GroupNumber getNumber() {
        return number;
    }

    public GroupEntity setNumber(GroupNumber number) {
        this.number = number;
        return this;
    }

    @Enumerated(EnumType.ORDINAL)
    public GroupLetter getLetter() {
        return letter;
    }

    public GroupEntity setLetter(GroupLetter letter) {
        this.letter = letter;
        return this;
    }
}
