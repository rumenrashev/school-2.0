package school.model.service;

import school.constants.enumuration.ClassroomLetter;
import school.constants.enumuration.ClassroomNumber;
import school.model.BaseModel;

import java.util.List;

public class ClassroomServiceModel extends BaseModel {

    private ClassroomNumber number;
    private ClassroomLetter letter;
    private List<SubjectServiceModel> subjects;
    private List<StudentServiceModel> students;

    public ClassroomServiceModel() {
    }

    public ClassroomNumber getNumber() {
        return number;
    }

    public ClassroomServiceModel setNumber(ClassroomNumber number) {
        this.number = number;
        return this;
    }

    public ClassroomLetter getLetter() {
        return letter;
    }

    public ClassroomServiceModel setLetter(ClassroomLetter letter) {
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
