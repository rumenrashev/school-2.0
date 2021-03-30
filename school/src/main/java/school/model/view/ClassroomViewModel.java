package school.model.view;

import school.constants.enumuration.GroupLetter;
import school.constants.enumuration.GroupNumber;
import school.model.BaseModel;

public class ClassroomViewModel extends BaseModel {

    private GroupNumber number;
    private GroupLetter letter;

    public ClassroomViewModel() {
    }

    public GroupNumber getNumber() {
        return number;
    }

    public ClassroomViewModel setNumber(GroupNumber number) {
        this.number = number;
        return this;
    }

    public GroupLetter getLetter() {
        return letter;
    }

    public ClassroomViewModel setLetter(GroupLetter letter) {
        this.letter = letter;
        return this;
    }
}
