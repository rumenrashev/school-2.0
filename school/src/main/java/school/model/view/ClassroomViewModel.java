package school.model.view;

import school.constants.enumuration.ClassroomLetter;
import school.constants.enumuration.ClassroomNumber;
import school.model.BaseModel;

public class ClassroomViewModel extends BaseModel {

    private ClassroomNumber number;
    private ClassroomLetter letter;

    public ClassroomViewModel() {
    }

    public ClassroomNumber getNumber() {
        return number;
    }

    public ClassroomViewModel setNumber(ClassroomNumber number) {
        this.number = number;
        return this;
    }

    public ClassroomLetter getLetter() {
        return letter;
    }

    public ClassroomViewModel setLetter(ClassroomLetter letter) {
        this.letter = letter;
        return this;
    }
}
