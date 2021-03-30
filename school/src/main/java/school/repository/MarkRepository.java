package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.model.entity.MarkEntity;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<MarkEntity, Long> {


    List<MarkEntity> findAllByStudent_IdAndSubject_Id(Long studentId, Long subjectId);

    List<MarkEntity> findAllByStudent_User_Username(String student_user_username);


}
