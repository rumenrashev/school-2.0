package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.constants.enumuration.ClassroomLetter;
import school.constants.enumuration.ClassroomNumber;
import school.model.entity.ClassroomEntity;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassroomEntity,Long> {

    boolean existsByNumberAndLetter(ClassroomNumber number, ClassroomLetter letter);

    @Query(value = "SELECT g FROM ClassroomEntity g ORDER BY g.number , g.letter")
    List<ClassroomEntity> getAllOrderByNumberAndLetter();

}
