package school.model.view;

import school.constants.enumuration.GroupLetter;
import school.constants.enumuration.GroupNumber;
import school.model.base.BaseModel;

import java.util.List;

public class GroupViewModel extends BaseModel {

    private GroupNumber number;
    private GroupLetter letter;
    private List<StudentViewModel> students;

    public GroupViewModel() {
    }

    public GroupNumber getNumber() {
        return number;
    }

    public GroupViewModel setNumber(GroupNumber number) {
        this.number = number;
        return this;
    }

    public GroupLetter getLetter() {
        return letter;
    }

    public GroupViewModel setLetter(GroupLetter letter) {
        this.letter = letter;
        return this;
    }

    public List<StudentViewModel> getStudents() {
        return students;
    }

    public GroupViewModel setStudents(List<StudentViewModel> students) {
        this.students = students;
        return this;
    }
}
