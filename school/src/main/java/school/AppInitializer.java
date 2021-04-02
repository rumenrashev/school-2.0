package school;

import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import school.model.entity.UserEntity;
import school.repository.UserRepository;
import school.assync.EmailSender;

import java.util.Optional;

@Component
public class AppInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailSender emailSender;
    private final PasswordGenerator passwordGenerator;

    @Autowired
    public AppInitializer(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, EmailSender emailSender, PasswordGenerator passwordGenerator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender = emailSender;
        this.passwordGenerator = passwordGenerator;
    }

    @Override
    public void run(String... args) throws Exception {
        encryptPassword("admin@gmail.com");
        encryptPassword("teacher@gmail.com");
        encryptPassword("student@gmail.com");
        encryptPassword("user@gmail.com");
    }

    private void encryptPassword(String email){
        Optional<UserEntity> optional = this.userRepository.findByEmail(email);
        if (optional.isPresent()){
            UserEntity userEntity = optional.get();
            String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
            userEntity.setPassword(encodedPassword);
            userRepository.save(userEntity);
        }
    }
}
