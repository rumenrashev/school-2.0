package school.model.binding;

import school.constants.enumuration.ClassroomLetter;
import school.constants.enumuration.ClassroomNumber;

public class ClassroomBindingModel {

    private ClassroomNumber number;
    private ClassroomLetter letter;

    public ClassroomBindingModel() {
    }

    public ClassroomNumber getNumber() {
        return number;
    }

    public ClassroomBindingModel setNumber(ClassroomNumber number) {
        this.number = number;
        return this;
    }

    public ClassroomLetter getLetter() {
        return letter;
    }

    public ClassroomBindingModel setLetter(ClassroomLetter letter) {
        this.letter = letter;
        return this;
    }
}
