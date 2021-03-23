package school.model.service;

import school.model.BaseModel;

import java.util.List;

public class TeacherServiceModel extends BaseModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private Long userId;
    private List<SubjectServiceModel> subjects;


    public TeacherServiceModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public TeacherServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public TeacherServiceModel setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public TeacherServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public TeacherServiceModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public List<SubjectServiceModel> getSubjects() {
        return subjects;
    }

    public TeacherServiceModel setSubjects(List<SubjectServiceModel> subjects) {
        this.subjects = subjects;
        return this;
    }
}
