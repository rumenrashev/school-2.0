package school.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import school.constants.enumuration.AuthorityEnum;
import school.exception.UserIdNotFountException;
import school.model.entity.UserEntity;
import school.model.service.UserServiceModel;
import school.repository.UserRepository;
import school.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @MockBean
    UserRepository userRepository;

    final String adminUsername = "admin";
    final String teacherUsername = "teacher";
    final String studentUsername = "student";
    final String userUsername = "user";
    final UserEntity adminEntity = new UserEntity().setEmail(adminUsername);
    final UserEntity teacherEntity = new UserEntity().setEmail(teacherUsername);
    final UserEntity studentEntity = new UserEntity().setEmail(studentUsername);
    final UserEntity userEntity = new UserEntity().setEmail(userUsername);
    final UserServiceModel adminSM = new UserServiceModel().setEmail(adminUsername);
    final UserServiceModel teacherSM = new UserServiceModel().setEmail(teacherUsername);
    final UserServiceModel studentSM = new UserServiceModel().setEmail(studentUsername);
    final UserServiceModel userSM = new UserServiceModel().setEmail(userUsername);

    @Test
    void getAllUsers_ShouldReturnFourUsers(){
        when(userRepository.findAllByAuthority(any()))
                .thenReturn(List.of(adminEntity,teacherEntity,studentEntity,userEntity));
        List<UserServiceModel> allUsers = userService.getAllUsers();
        assertEquals(4,allUsers.size());
    }

    @Test
    void deleteUser_ShouldWorkCallUserRepository(){
        userService.deleteUser(any());
        int calls = Mockito.mockingDetails(userRepository).getInvocations().size();
        assertEquals(1,calls);

    }

    @Test
    void getUserById_ShouldWorkCorrect(){
        when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        UserServiceModel actual = userService.getUser(any());
        assertEquals(userSM.getEmail(),actual.getEmail());
    }

    @Test
    void addAuthority_ShouldThrowExeption(){
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(UserIdNotFountException.class,
                ()-> userService.addAuthority(1L,"ADMIN"));

    }

    @Test
    void removeAuthority_ShouldThrowExeption() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(UserIdNotFountException.class,
                ()-> userService.removeAuthority(1L,"ADMIN"));

    }

    @Test
    void getAllTeachers_ShouldWorkCorrect(){
        when(userRepository.findAllByAuthority(AuthorityEnum.TEACHER.name()))
                .thenReturn(List.of(teacherEntity));
        List<UserServiceModel> teachers = userService.getAllTeachers();
        UserServiceModel actualTeacher = teachers.get(0);
        assertEquals(1,teachers.size());
        assertEquals(teacherUsername,actualTeacher.getEmail());
    }

    @Test
    void getAllAdmins_ShouldWorkCorrect(){
        when(userRepository.findAllByAuthority(AuthorityEnum.ADMIN.name()))
                .thenReturn(List.of(adminEntity));
        List<UserServiceModel> admins = userService.getAllAdmins();
        UserServiceModel actualAdmin = admins.get(0);
        assertEquals(1,admins.size());
        assertEquals(adminUsername,actualAdmin.getEmail());
    }

    @Test
    void getUsersCount_ShouldWorkCorrect(){
        final long count = 5L;
        when(userRepository.count()).thenReturn(count);
        long actualCount = userService.getUserCount();
        assertEquals(count,actualCount);
    }




}
