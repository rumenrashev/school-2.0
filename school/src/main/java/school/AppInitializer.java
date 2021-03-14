package school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import school.service.AuthorityService;
import school.service.UserService;

@Component
public class AppInitializer implements CommandLineRunner {

    private final AuthorityService authorityService;
    private final UserService userService;

    @Autowired
    public AppInitializer(AuthorityService authorityService, UserService userService) {
        this.authorityService = authorityService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.authorityService.seedRoles();
        this.userService.seedTestUsers();
    }
}
