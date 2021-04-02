package school.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import school.constants.enumuration.ClassroomLetter;
import school.constants.enumuration.ClassroomNumber;
import school.model.entity.ClassroomEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class ClassroomRepositoryTest {

    @Autowired
    ClassroomRepository classroomRepository;

    @Test
    void getAllOrderByNumberAndLetter_ShouldOrderCorrect(){
        ClassroomEntity classroomEntity1 =
                new ClassroomEntity().setNumber(ClassroomNumber.III)
                        .setLetter(ClassroomLetter.А);
        ClassroomEntity classroomEntity2 =
                new ClassroomEntity().setNumber(ClassroomNumber.I)
                .setLetter(ClassroomLetter.Б);
        ClassroomEntity classroomEntity3 =
                new ClassroomEntity().setNumber(ClassroomNumber.I)
                .setLetter(ClassroomLetter.А);
                classroomRepository.saveAll(List.of(classroomEntity1, classroomEntity2, classroomEntity3));
        List<ClassroomEntity> classroomEntities = classroomRepository.getAllOrderByNumberAndLetter();

        Assert.assertEquals(classroomEntities.get(0).getNumber(),classroomEntity3.getNumber());
        Assert.assertEquals(classroomEntities.get(0).getLetter(),classroomEntity3.getLetter());
    }
}
