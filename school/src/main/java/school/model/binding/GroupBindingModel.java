package school.model.binding;

import school.constants.enumuration.GroupLetter;
import school.constants.enumuration.GroupNumber;

public class GroupBindingModel {

    private GroupNumber number;
    private GroupLetter letter;

    public GroupBindingModel() {
    }

    public GroupNumber getNumber() {
        return number;
    }

    public GroupBindingModel setNumber(GroupNumber number) {
        this.number = number;
        return this;
    }

    public GroupLetter getLetter() {
        return letter;
    }

    public GroupBindingModel setLetter(GroupLetter letter) {
        this.letter = letter;
        return this;
    }
}
