package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.constants.enumuration.GroupLetter;
import school.constants.enumuration.GroupNumber;
import school.model.entity.GroupEntity;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity,Long> {

    boolean existsByNumberAndLetter(GroupNumber number, GroupLetter letter);

    @Query(value = "SELECT g FROM GroupEntity g ORDER BY g.number , g.letter")
    List<GroupEntity> getAllOrderByNumberAndLetter();

}
