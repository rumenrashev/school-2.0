package school.model.binding;

import school.constants.enumuration.GroupLetter;
import school.constants.enumuration.GroupNumber;

public class ClassroomBindingModel {

    private GroupNumber number;
    private GroupLetter letter;

    public ClassroomBindingModel() {
    }

    public GroupNumber getNumber() {
        return number;
    }

    public ClassroomBindingModel setNumber(GroupNumber number) {
        this.number = number;
        return this;
    }

    public GroupLetter getLetter() {
        return letter;
    }

    public ClassroomBindingModel setLetter(GroupLetter letter) {
        this.letter = letter;
        return this;
    }
}
