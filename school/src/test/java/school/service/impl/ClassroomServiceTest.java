package school.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import school.constants.enumuration.GroupLetter;
import school.constants.enumuration.GroupNumber;
import school.exception.ClassroomNotFountException;
import school.model.entity.ClassroomEntity;
import school.model.service.ClassroomServiceModel;
import school.repository.ClassroomRepository;
import school.service.ClassroomService;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class ClassroomServiceTest {

    @Autowired
    ClassroomService classroomService;

    @MockBean
    ClassroomRepository classroomRepository;

    final ClassroomEntity classroomEntity1 =
            new ClassroomEntity().setNumber(GroupNumber.I).setLetter(GroupLetter.А);
    final ClassroomEntity classroomEntity2 =
            new ClassroomEntity().setNumber(GroupNumber.I).setLetter(GroupLetter.Б);

    final ClassroomServiceModel classroomServiceModel1 =
            new ClassroomServiceModel().setNumber(GroupNumber.I).setLetter(GroupLetter.А);
    final ClassroomServiceModel classroomServiceModel2 =
            new ClassroomServiceModel().setNumber(GroupNumber.I).setLetter(GroupLetter.Б);

    @Test
    void createClassroom_ShouldReturnFalse() {
        when(classroomRepository.existsByNumberAndLetter(any(), any())).thenReturn(true);
        assertFalse(classroomService.createClassroom(classroomServiceModel1));
    }

    @Test
    void createClassroom_ShouldReturnTrue() {
        when(classroomRepository.existsByNumberAndLetter(any(), any())).thenReturn(false);
        assertTrue(classroomService.createClassroom(classroomServiceModel1));
    }

    @Test
    void getAll_ShouldWorkCorrect() {
        when(classroomRepository.getAllOrderByNumberAndLetter())
                .thenReturn(List.of(classroomEntity1, classroomEntity2));
        List<ClassroomServiceModel> all = classroomService.getAll();
        assertEquals(classroomServiceModel1.getNumber(), all.get(0).getNumber());
        assertEquals(classroomServiceModel1.getLetter(), all.get(0).getLetter());
        assertEquals(classroomServiceModel2.getNumber(), all.get(1).getNumber());
        assertEquals(classroomServiceModel2.getLetter(), all.get(1).getLetter());
    }

    @Test
    void getById_ShouldReturnServiceModel() {
        when(classroomRepository.findById(any()))
                .thenReturn(Optional.of(classroomEntity1));
        ClassroomServiceModel actualServiceModel = classroomService.getById(any());
        GroupNumber actualNumber = actualServiceModel.getNumber();
        GroupLetter actualLetter = actualServiceModel.getLetter();
        assertEquals(classroomServiceModel1.getNumber(), actualNumber);
        assertEquals(classroomServiceModel1.getLetter(), actualLetter);
    }

    @Test
    void getById_ShouldThrowException() {
        when(classroomRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(ClassroomNotFountException.class,
                () -> classroomService.getById(any()));
    }

    @Test
    void getClassroomCount_ShouldWorkCorrect() {
        when(classroomRepository.count()).thenReturn(5L);
        assertEquals(5L, classroomService.getClassroomsCount());
    }
}
