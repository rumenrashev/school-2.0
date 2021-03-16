package school.model.service;

import school.constants.enumuration.GroupLetter;
import school.constants.enumuration.GroupNumber;
import school.model.base.BaseModel;

import java.util.List;

public class GroupServiceModel extends BaseModel {

    private GroupNumber number;
    private GroupLetter letter;
    private List<StudentServiceModel> students;

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

    public List<StudentServiceModel> getStudents() {
        return students;
    }

    public GroupServiceModel setStudents(List<StudentServiceModel> students) {
        this.students = students;
        return this;
    }
}
