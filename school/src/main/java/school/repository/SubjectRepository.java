package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.constants.enumuration.SubjectEnum;
import school.model.entity.SubjectEntity;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity,Long> {

    boolean existsBySubjectAndGroupId(SubjectEnum subject, Long group_id);
}
