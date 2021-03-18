package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.model.entity.StudentEntity;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {


    @Query(value = "SELECT s " +
            "FROM StudentEntity s " +
            "WHERE s.group.id = ?1 " +
            "ORDER BY s.firstName,s.middleName,s.lastName")
    List<StudentEntity> findAllByGroupId(Long groupId);

}
