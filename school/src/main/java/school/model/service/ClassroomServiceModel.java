package school.model.service;

import school.constants.enumuration.GroupLetter;
import school.constants.enumuration.GroupNumber;
import school.model.BaseModel;

import java.util.List;

public class ClassroomServiceModel extends BaseModel {

    private GroupNumber number;
    private GroupLetter letter;
    private List<SubjectServiceModel> subjects;
    private List<StudentServiceModel> students;

    public ClassroomServiceModel() {
    }

    public GroupNumber getNumber() {
        return number;
    }

    public ClassroomServiceModel setNumber(GroupNumber number) {
        this.number = number;
        return this;
    }

    public GroupLetter getLetter() {
        return letter;
    }

    public ClassroomServiceModel setLetter(GroupLetter letter) {
        this.letter = letter;
        return this;
    }

    public List<SubjectServiceModel> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectServiceModel> subjects) {
        this.subjects = subjects;
    }

    public List<StudentServiceModel> getStudents() {
        return students;
    }

    public ClassroomServiceModel setStudents(List<StudentServiceModel> students) {
        this.students = students;
        return this;
    }
}
