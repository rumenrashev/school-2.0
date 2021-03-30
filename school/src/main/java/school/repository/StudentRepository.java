package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.model.entity.StudentEntity;
import school.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {


    @Query(value = "SELECT s " +
            "FROM StudentEntity s " +
            "WHERE s.classroom.id = ?1 " +
            "ORDER BY s.firstName,s.middleName,s.lastName")
    List<StudentEntity> findAllByGroupId(Long groupId);

    boolean existsByUser_Id(Long userId);

    Optional<StudentEntity> findByUser_Username(String username);

//    @Query(value = "SELECT u FROM UserEntity u " +
//            "WHERE u.authorities == ?1")
//    List<UserEntity> findAllFreeStudentsUsers();
}
