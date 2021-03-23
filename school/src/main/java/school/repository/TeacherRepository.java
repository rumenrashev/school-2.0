package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.model.entity.TeacherEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity,Long> {


    boolean existsByUserId(Long userId);

    TeacherEntity findByUser_Username(String username);

    Optional<TeacherEntity> findByUser_Id(Long userId);


}
