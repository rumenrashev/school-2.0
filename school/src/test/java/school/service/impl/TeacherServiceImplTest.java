package school.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import school.constants.enumuration.AuthorityEnum;
import school.exception.TeacherNotFoundException;
import school.model.entity.TeacherEntity;
import school.model.entity.UserEntity;
import school.model.service.TeacherServiceModel;
import school.model.service.UserServiceModel;
import school.repository.TeacherRepository;
import school.repository.UserRepository;
import school.service.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class TeacherServiceImplTest {

    @Autowired
    TeacherService teacherService;
    @MockBean
    TeacherRepository teacherRepository;
    @MockBean
    UserRepository userRepository;

    final String firstName = "Aaaaa";
    final String lastName = "Bbbbb";
    final String middleName = "Ccccc";
    final TeacherEntity teacherEntity =
            new TeacherEntity()
                    .setFirstName(firstName)
                    .setMiddleName(middleName)
                    .setLastName(lastName)
                    .setSubjects(new ArrayList<>());
    final TeacherServiceModel teacherServiceModel =
            new TeacherServiceModel()
                    .setFirstName(firstName)
                    .setMiddleName(middleName)
                    .setLastName(lastName);


    @Test
    void addTeacher_ShouldReturnValidUserServiceModel() {
        when(teacherRepository.save(any()))
                .thenReturn(teacherEntity);
        TeacherServiceModel actualServiceModel =
                teacherService.addTeacher(this.teacherServiceModel);
        assertEquals(firstName, actualServiceModel.getFirstName());
        assertEquals(middleName, actualServiceModel.getMiddleName());
        assertEquals(lastName, actualServiceModel.getLastName());
    }

    @Test
    void getAllTeachers_ShouldWorkCorrect() {
        when(teacherRepository.findAll())
                .thenReturn(List.of(teacherEntity));
        List<TeacherServiceModel> allTeachers = teacherService.getAllTeachers();
        TeacherServiceModel teacherServiceModel = allTeachers.get(0);
        assertEquals(1, allTeachers.size());
        assertEquals(firstName, teacherServiceModel.getFirstName());
        assertEquals(middleName, teacherServiceModel.getMiddleName());
        assertEquals(lastName, teacherServiceModel.getLastName());
    }

    @Test
    void editTeacher_ShouldWorkCorrect() {
        final String editFirstName = "Ddddd";
        when(teacherRepository.save(any()))
                .thenReturn(new TeacherEntity()
                        .setFirstName(editFirstName)
                        .setMiddleName(middleName)
                        .setLastName(lastName));
        TeacherServiceModel teacherServiceModel =
                teacherService.editTeacher(new TeacherServiceModel()
                .setFirstName(editFirstName)
                .setMiddleName(middleName)
                .setLastName(lastName));
        assertEquals(editFirstName,teacherServiceModel.getFirstName());
        assertEquals(middleName,teacherServiceModel.getMiddleName());
        assertEquals(lastName,teacherServiceModel.getLastName());
    }

    @Test
    void getTeacherById_ShouldThrowException(){
        when(teacherRepository.findById(any()))
                .thenReturn(Optional.empty());
        assertThrows(TeacherNotFoundException.class,
                ()-> teacherService.getTeacherById(any()));

    }

    @Test
    void getTeacherById_ShouldReturnServiceModel(){
        when(teacherRepository.findById(any()))
                .thenReturn(Optional.of(teacherEntity));
        TeacherServiceModel actual = teacherService.getTeacherById(any());
        assertEquals(firstName,actual.getFirstName());
        assertEquals(middleName,actual.getMiddleName());
        assertEquals(lastName,actual.getLastName());
    }

    @Test
    void existByUserId_ShouldReturnTrue(){
        when(teacherRepository.existsByUserId(anyLong()))
                .thenReturn(true);
        boolean actual = teacherService.existByUserId(anyLong());
        assertTrue(actual);
    }

    @Test
    void existByUserId_ShouldReturnFalse(){
        when(teacherRepository.existsByUserId(anyLong()))
                .thenReturn(false);
        boolean actual = teacherService.existByUserId(anyLong());
        assertFalse(actual);
    }


    @Test
    void getTeachersCount_ShouldWorkCorrect(){
        final Long expected = 1L;
        when(teacherRepository.count())
                .thenReturn(expected);
        long actual = teacherService.getTeachersCount();
        assertEquals(expected,actual);
    }

    @Test
    void getAllFreeTeachers_ShouldWorkCorrect(){
        final String username = "username";
        when(userRepository.findAllByAuthority(AuthorityEnum.TEACHER.name()))
                .thenReturn(List.of(new UserEntity().setUsername(username)));
        List<UserServiceModel> allFreeTeachersUsers = teacherService.getAllFreeTeachersUsers();
        UserServiceModel actual = allFreeTeachersUsers.get(0);
        assertEquals(username,actual.getUsername());
    }

    @Test
    void deleteTeacher_ShouldThrowException(){
        when(teacherRepository.findById(any()))
                .thenReturn(Optional.empty());
        assertThrows(TeacherNotFoundException.class,
                ()-> teacherService.deleteTeacher(any()));
    }

    @Test
    void deleteTeacher_ShouldNotThrowException(){
        when(teacherRepository.findById(any()))
                .thenReturn(Optional.of(teacherEntity));
        teacherService.deleteTeacher(any());
    }
}
