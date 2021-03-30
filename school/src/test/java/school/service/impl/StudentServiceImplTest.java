package school.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import school.constants.enumuration.AuthorityEnum;
import school.exception.StudentIdNotFoundException;
import school.exception.StudentUsernameNoFound;
import school.model.entity.StudentEntity;
import school.model.entity.UserEntity;
import school.model.service.StudentServiceModel;
import school.model.service.UserServiceModel;
import school.repository.StudentRepository;
import school.repository.UserRepository;
import school.service.StudentService;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class StudentServiceImplTest {

    @Autowired
    StudentService studentService;
    @MockBean
    StudentRepository studentRepository;
    @MockBean
    UserRepository userRepository;

    final String firstName = "First";
    final String middleName = "Second";
    final String lastName = "Third";
    final StudentEntity studentE = new StudentEntity()
            .setFirstName(firstName)
            .setMiddleName(middleName)
            .setLastName(lastName);
    final StudentServiceModel studentSM = new StudentServiceModel()
            .setFirstName(firstName)
            .setMiddleName(middleName)
            .setLastName(lastName);

    @Test
    void addStudent_ShouldReturnServiceModel() {
        when(studentRepository.saveAndFlush(any()))
                .thenReturn(studentE);
        StudentServiceModel actual = studentService.addStudent(studentSM);
        assertEquals(firstName, actual.getFirstName());
        assertEquals(middleName, actual.getMiddleName());
        assertEquals(lastName, actual.getLastName());
    }

    @Test
    void getStudentsByClassId_ShouldWorkCorrect() {
        when(studentRepository.findAllByGroupId(any()))
                .thenReturn(List.of(studentE));
        List<StudentServiceModel> students = studentService.getStudentsByClassId(any());
        assertEquals(1, students.size());
        assertEquals(firstName, students.get(0).getFirstName());
    }

    @Test
    void getStudentById_ShouldThrowException() {
        when(studentRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        assertThrows(StudentIdNotFoundException.class,
                () -> studentService.getStudentById(anyLong()));
    }

    @Test
    void getStudentById_ShouldReturnServiceModel() {
        when(studentRepository.findById(anyLong()))
                .thenReturn(Optional.of(studentE));
        StudentServiceModel actual = studentService.getStudentById(anyLong());
        assertEquals(firstName, actual.getFirstName());
        assertEquals(middleName, actual.getMiddleName());
        assertEquals(lastName, actual.getLastName());
    }

    @Test
    void editStudent_ShouldReturnValidServiceModel() {
        when(studentRepository.saveAndFlush(any()))
                .thenReturn(studentE);
        StudentServiceModel actual = studentService.addStudent(studentSM);
        assertEquals(firstName, actual.getFirstName());
        assertEquals(middleName, actual.getMiddleName());
        assertEquals(lastName, actual.getLastName());
    }

    @Test
    void deleteStudentShouldReturnTrue() {
        boolean actual = studentService.deleteStudent(anyLong());
        assertTrue(actual);
    }

    @Test
    void getStudentByUserUsername_ShouldReturnValidServiceModel() {
        when(studentRepository.findByUser_Username(any()))
                .thenReturn(Optional.of(studentE));
        StudentServiceModel actual = studentService.getStudentByUserUsername(any());
        assertEquals(firstName, actual.getFirstName());
        assertEquals(middleName, actual.getMiddleName());
        assertEquals(lastName, actual.getLastName());
    }

    @Test
    void getStudentByUserUsername_ShouldThrowException() {
        when(studentRepository.findByUser_Username(any()))
                .thenReturn(Optional.empty());
        assertThrows(StudentUsernameNoFound.class,
                () -> studentService.getStudentByUserUsername(any()));
    }

    @Test
    void getAllFreeStudentsShouldWorkCorrect() {
        final String username = "username";
        when(userRepository.findAllByAuthority(AuthorityEnum.STUDENT.name()))
                .thenReturn(List.of(new UserEntity().setUsername(username)));
        List<UserServiceModel> allFreeTeachersUsers = studentService.getAllFreeStudentUsers();
        UserServiceModel actual = allFreeTeachersUsers.get(0);
        assertEquals(username, actual.getUsername());
    }
}
