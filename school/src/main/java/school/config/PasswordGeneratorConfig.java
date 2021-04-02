package school.config;

import org.passay.PasswordGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordGeneratorConfig {

    @Bean
    PasswordGenerator passwordGenerator() {
        return new PasswordGenerator();
    }
}
