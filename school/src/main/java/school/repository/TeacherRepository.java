package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.model.entity.TeacherEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity,Long> {


    boolean existsByUserId(Long userId);

    TeacherEntity findByUserUsername(String username);

    Optional<TeacherEntity> findByUserId(Long userId);

    @Query("SELECT t FROM TeacherEntity t ORDER BY t.firstName , t.middleName , t.lastName")
    List<TeacherEntity> findAll();


}
