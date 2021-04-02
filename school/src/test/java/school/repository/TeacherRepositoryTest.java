package school.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import school.model.entity.TeacherEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class TeacherRepositoryTest {

    @Autowired
    TeacherRepository teacherRepository;


    @Test
    void findAll_ShouldOrderCorrect(){
        TeacherEntity teacherEntity1 =
                new TeacherEntity().setFirstName("a").setMiddleName("б").setLastName("г");
        TeacherEntity teacherEntity2 =
                new TeacherEntity().setFirstName("a").setMiddleName("б").setLastName("в");
        teacherRepository.saveAll(List.of(teacherEntity1,teacherEntity2));
        List<TeacherEntity> all = teacherRepository.findAll();
        Assert.assertEquals(all.get(0).getLastName(),teacherEntity2.getLastName());
    }
}
