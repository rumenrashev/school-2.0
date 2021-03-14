package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsername(String username);

    @Query(value = "SELECT * FROM users u " +
            "JOIN users_authorities ua on u.id = ua.user_id " +
            "JOIN authorities a on a.id = ua.authority_id " +
            "WHERE a.authority = ?1",nativeQuery = true)
    List<UserEntity> findAllByAuthority(String authority);

    boolean existsByUsername(String username);

}
