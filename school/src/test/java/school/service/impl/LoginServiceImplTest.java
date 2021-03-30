package school.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import school.model.entity.UserEntity;
import school.repository.UserRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class LoginServiceImplTest {

    @Autowired
    LoginServiceImpl loginService;

    @MockBean
    UserRepository userRepository;

    final UserEntity userEntity = new UserEntity().setUsername("user").setPassword("user");

    @Test
    void loadUserByUsername_ShouldReturnUserDetails() {
        when(userRepository.findByUsername(any()))
                .thenReturn(Optional.of(userEntity));
        UserDetails userDetails = loginService.loadUserByUsername(any());
        assertEquals(userDetails.getUsername(), userEntity.getUsername());
        assertEquals(userDetails.getPassword(), userEntity.getPassword());
    }

    @Test
    void loadUserByUsername_ShouldThrowException() {
        when(userRepository.findByUsername(any()))
                .thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class,
                () -> loginService.loadUserByUsername(any()));
    }
}
