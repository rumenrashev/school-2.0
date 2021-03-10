package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.model.entity.AuthorityEntity;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

    boolean existsByAuthority(String authority);

    Optional<AuthorityEntity> findByAuthority(String authority);

}
