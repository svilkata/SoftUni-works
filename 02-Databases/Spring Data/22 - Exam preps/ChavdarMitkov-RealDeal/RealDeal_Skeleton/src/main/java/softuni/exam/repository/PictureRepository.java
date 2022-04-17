package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Picture;

import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query("SELECT p FROM Picture p" +
            " WHERE p.name =:name AND p.car.id =:car")
    Optional<Picture> findByNameAndCarId(String name, Long car);

}
