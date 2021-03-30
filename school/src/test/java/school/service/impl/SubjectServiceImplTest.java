package school.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import school.constants.enumuration.GroupLetter;
import school.constants.enumuration.GroupNumber;
import school.constants.enumuration.SubjectEnum;
import school.exception.SubjectIdNotFoundException;
import school.model.entity.ClassroomEntity;
import school.model.entity.SubjectEntity;
import school.model.service.SubjectServiceModel;
import school.repository.SubjectRepository;
import school.repository.TeacherRepository;
import school.service.SubjectService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class SubjectServiceImplTest {

    @Autowired
    SubjectService subjectService;

    @MockBean
    SubjectRepository subjectRepository;
    @MockBean
    TeacherRepository teacherRepository;

    final SubjectEnum subjectEnum = SubjectEnum.БЕЛ;
    final SubjectEntity subjectE =
            new SubjectEntity()
            .setSubject(subjectEnum);
    final SubjectServiceModel subjectSM =
            new SubjectServiceModel()
                    .setSubject(SubjectEnum.БЕЛ);

    @Test
    void addSubjectShouldWorkCorrect(){
        when(subjectRepository.save(any()))
                .thenReturn(subjectE);
        SubjectServiceModel actual = subjectService.addSubject(subjectSM);
        assertEquals(subjectEnum,actual.getSubject());
    }

    @Test
    void getAllSubjectsByClassId_ShouldWorkCorrect(){
        when(subjectRepository.findAllByClassroomId(any()))
                .thenReturn(List.of(subjectE));
        List<SubjectServiceModel> actualList = subjectService.getAllSubjectsByClassId(any());
        SubjectServiceModel actualSubject =actualList.get(0);
        assertEquals(1,actualList.size());
        assertEquals(subjectEnum,actualSubject.getSubject());
    }

    @Test
    void deleteSubject_ShouldReturnTrue(){
        boolean actual = subjectService.deleteSubject(anyLong());
        assertEquals(true,actual);
    }

    @Test
    void subjectExists_ShouldReturnTrue(){
        when(subjectRepository.existsBySubjectAndClassroomId(any(),any()))
                .thenReturn(true);
        assertTrue(subjectService.subjectExists(any(),any()));
    }

    @Test
    void subjectExists_ShouldReturnFalse(){
        when(subjectRepository.existsBySubjectAndClassroomId(any(),any()))
                .thenReturn(false);
        assertFalse(subjectService.subjectExists(any(),any()));
    }

    @Test
    void getSubjectById_ShouldThrowException(){
        when(subjectRepository.findById(any()))
                .thenReturn(Optional.empty());
        assertThrows(SubjectIdNotFoundException.class,
                ()->subjectService.getSubjectById(any()));
    }

    @Test
    void getSubjectById_ShouldReturnServiceModel(){
        when(subjectRepository.findById(any()))
                .thenReturn(Optional.of(subjectE));
        SubjectServiceModel actual = subjectService.getSubjectById(any());
        assertEquals(subjectEnum,actual.getSubject());
    }

    @Test
    void editSubject_ShouldWorkCorrect(){
        when(subjectRepository.save(any()))
                .thenReturn(subjectE);
        SubjectServiceModel actual = subjectService.addSubject(subjectSM);
        assertEquals(subjectEnum,actual.getSubject());
    }

}
