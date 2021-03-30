package school.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import school.constants.enumuration.AuthorityEnum;
import school.repository.AuthorityRepository;
import school.service.AuthorityService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mockingDetails;

@SpringBootTest
@RunWith(SpringRunner.class)
class AuthorityServiceImplTest {

    @Autowired
    AuthorityService authorityService;

    @MockBean
    AuthorityRepository mockAuthorityRepository;


    @Test
    public void seedRolesShouldWorkCorrectly() {
        authorityService.seedRoles();
        int actual = mockingDetails(mockAuthorityRepository).getInvocations().size();
        int expected = AuthorityEnum.values().length * 2;
        assertEquals(expected, actual);
    }
}
