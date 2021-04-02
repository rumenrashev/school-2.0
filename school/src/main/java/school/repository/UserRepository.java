package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.model.entity.StudentEntity;
import school.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);

    @Query(value = "SELECT  * FROM users u " +
            "JOIN users_authorities ua on u.id = ua.user_id " +
            "JOIN authorities a on ua.authority_id = a.id " +
            "WHERE a.authority = ?1 " +
            "ORDER BY u.email",
    nativeQuery = true)
    List<UserEntity> findAllByAuthority(String authority);

    boolean existsByEmail(String email);

}
