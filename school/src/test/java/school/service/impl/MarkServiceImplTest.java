package school.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import school.model.entity.MarkEntity;
import school.model.service.MarkServiceModel;
import school.repository.MarkRepository;
import school.repository.StudentRepository;
import school.repository.SubjectRepository;
import school.service.MarkService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class MarkServiceImplTest {

    @Autowired
    MarkService markService;
    @MockBean
    MarkRepository markRepository;

    final int value = 6;
    final int value2 = 2;
    final MarkEntity entity = new MarkEntity().setValue(value);
    final MarkEntity entity2 = new MarkEntity().setValue(value2);
    final MarkServiceModel serviceModel = new MarkServiceModel().setValue(value);

    @Test
    void addMark_ShouldWorkCorrect() {
        when(markRepository.save(any()))
                .thenReturn(entity);
        MarkServiceModel actual = markService.addMark(this.serviceModel);
        assertEquals(value, actual.getValue());
    }

    @Test
    void getMarksByStudentAndSubject_ShouldWorkCorrect() {
        when(markRepository.findAllByStudent_IdAndSubject_Id(any(), any()))
                .thenReturn(List.of(entity));
        List<MarkServiceModel> actualList = markService.getMarksByStudentAndSubject(any(), any());
        MarkServiceModel actualServiceModel = actualList.get(0);
        assertEquals(1, actualList.size());
        assertEquals(serviceModel.getValue(), actualServiceModel.getValue());
    }

    @Test
    void deleteMark_ShouldReturnTrue() {
        boolean actual = markService.deleteMark(any());
        assertTrue(actual);
    }

    @Test
    void editMark_ShouldWorkCorrect() {
        when(markRepository.save(any()))
                .thenReturn(entity);
        MarkServiceModel actual = markService.addMark(this.serviceModel);
        assertEquals(value, actual.getValue());
    }

    @Test
    void getStudentAverageMark_ShouldWorkCorrect() {
        final List<MarkServiceModel> marks =
                List.of(serviceModel, new MarkServiceModel().setValue(5));
        Double actual = markService.getStudentAverageMark(marks);
        assertEquals(5.5, actual);
    }


    @Test
    void  getMarksCount_ShouldWorkCorrect(){
        final long expected = 5L;
        when(markRepository.count())
                .thenReturn(expected);
        long actual = markService.getMarksCount();
        assertEquals(expected,actual);
    }

    }
