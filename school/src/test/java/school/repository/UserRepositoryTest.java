package school.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import school.model.entity.AuthorityEntity;
import school.model.entity.UserEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserRepositoryTest {

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void findByAuthority_ShouldWorkCorrect(){
        final String ADMIN = "ADMIN";
        final String USER = "USER";
        AuthorityEntity adminAuthority = new AuthorityEntity().setAuthority(ADMIN);
        adminAuthority = authorityRepository.saveAndFlush(adminAuthority);
        AuthorityEntity userAuthority = new AuthorityEntity().setAuthority(USER);
        userAuthority = authorityRepository.saveAndFlush(userAuthority);
        UserEntity adminEnity = new UserEntity().setEmail("б").setPassword("pass").setAuthorities(List.of(adminAuthority));
        UserEntity userEntity1 = new UserEntity().setEmail("в").setPassword("pass").setAuthorities(List.of(userAuthority));
        UserEntity userEntity2 = new UserEntity().setEmail("a").setPassword("pass").setAuthorities(List.of(userAuthority));
        userRepository.saveAll(List.of(adminEnity,userEntity1,userEntity2));
        List<UserEntity> users = userRepository.findAllByAuthority(USER);
        Assert.assertEquals(2,users.size());
        Assert.assertEquals("a",users.get(0).getEmail());
    }

}
