package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.model.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
}
