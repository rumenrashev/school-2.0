package school.model.service;

import school.constants.enumuration.GroupLetter;
import school.constants.enumuration.GroupNumber;
import school.model.BaseModel;
import school.service.GroupService;

import java.util.List;

public class GroupServiceModel extends BaseModel {

    private GroupNumber number;
    private GroupLetter letter;
    private GroupServiceModel group;

    public GroupServiceModel() {
    }

    public GroupNumber getNumber() {
        return number;
    }

    public GroupServiceModel setNumber(GroupNumber number) {
        this.number = number;
        return this;
    }

    public GroupLetter getLetter() {
        return letter;
    }

    public GroupServiceModel setLetter(GroupLetter letter) {
        this.letter = letter;
        return this;
    }

    public GroupServiceModel getGroup() {
        return group;
    }

    public GroupServiceModel setGroup(GroupServiceModel group) {
        this.group = group;
        return this;
    }
}
