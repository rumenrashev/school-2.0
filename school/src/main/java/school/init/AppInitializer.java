package school.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import school.service.AuthorityService;

@Component
public class AppInitializer implements CommandLineRunner {

    private final AuthorityService authorityService;

    @Autowired
    public AppInitializer(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.authorityService.seedRoles();
    }
}
