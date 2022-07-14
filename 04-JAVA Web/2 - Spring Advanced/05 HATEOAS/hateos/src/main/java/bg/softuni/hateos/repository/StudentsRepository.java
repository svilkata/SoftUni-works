package bg.softuni.hateos.repository;

import bg.softuni.hateos.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<StudentEntity, Long> {
}
