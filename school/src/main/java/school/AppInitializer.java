package school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import school.model.entity.UserEntity;
import school.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AppInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AppInitializer(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        encryptPassword("admin");
        encryptPassword("teacher");
        encryptPassword("student");
        encryptPassword("user");
    }

    private void encryptPassword(String username){
        Optional<UserEntity> optional = this.userRepository.findByUsername(username);
        if (optional.isPresent()){
            UserEntity userEntity = optional.get();
            String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
            userEntity.setPassword(encodedPassword);
            userRepository.save(userEntity);
        }
    }
}
