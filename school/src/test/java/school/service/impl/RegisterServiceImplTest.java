package school.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import school.constants.enumuration.AuthorityEnum;
import school.exception.AuthorityNotFoundException;
import school.model.entity.AuthorityEntity;
import school.model.entity.UserEntity;
import school.model.service.AuthorityServiceModel;
import school.model.service.UserAuthenticationServiceModel;
import school.model.service.UserServiceModel;
import school.repository.AuthorityRepository;
import school.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class RegisterServiceImplTest {

    @Autowired
    RegisterServiceImpl registerService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    AuthorityRepository authorityRepository;

    final String username = "username";
    final String password = "password";
    final String authority = AuthorityEnum.USER.name();
    final AuthorityEntity authorityEntity =
            new AuthorityEntity()
                    .setAuthority(authority);
    final AuthorityServiceModel authorityServiceModel =
            new AuthorityServiceModel()
                    .setAuthority(authority);
    final UserEntity userEntity =
            new UserEntity()
                    .setUsername(username)
                    .setPassword(password)
                    .setAuthorities(List.of(authorityEntity));
    final UserAuthenticationServiceModel userServiceModel =
            new UserAuthenticationServiceModel()
                    .setUsername(username)
                    .setPassword(password)
                    .setAuthorities(List.of(authorityServiceModel));

    @Test
    void registerUser_ShouldRegisterUserAndReturnUserServiceModel() {
        when(authorityRepository.findByAuthority(authority))
                .thenReturn(Optional.of(authorityEntity));
        when(userRepository.saveAndFlush(any())).thenReturn(userEntity);
        UserAuthenticationServiceModel result = registerService.registerUser(this.userServiceModel);
        assertEquals(userServiceModel.getUsername(), result.getUsername());
        assertEquals(userServiceModel.getPassword(), result.getPassword());
        assertEquals(userServiceModel.getAuthorities().size(), result.getAuthorities().size());
    }

    @Test
    void registerUser_ShouldThrowException() {
        when(authorityRepository.findByAuthority(authority))
                .thenReturn(Optional.empty());
        assertThrows(AuthorityNotFoundException.class,
                () -> registerService.registerUser(userServiceModel));
    }
}
