package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.constants.enumuration.SubjectEnum;
import school.model.entity.SubjectEntity;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

    boolean existsBySubjectAndClassroomId(SubjectEnum subject, Long group_id);

    List<SubjectEntity> findAllByClassroomId(Long groupId);

    List<SubjectEntity> findAllByTeacherUserUsername(String teacher_user_username);

    List<SubjectEntity> findAllByTeacherId(Long id);


}
