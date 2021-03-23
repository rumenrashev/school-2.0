package school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import school.model.entity.UserEntity;
import school.repository.UserRepository;

import java.util.List;

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
        UserEntity admin = userRepository.findByUsername("admin").orElseThrow();
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        UserEntity teacher = userRepository.findByUsername("teacher").orElseThrow();
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        UserEntity user = userRepository.findByUsername("user").orElseThrow();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity student = userRepository.findByUsername("student").orElseThrow();
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        userRepository.saveAll(List.of(admin,teacher,user,student));
    }
}
