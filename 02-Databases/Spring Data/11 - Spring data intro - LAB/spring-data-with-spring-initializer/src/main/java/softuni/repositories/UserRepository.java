package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.models.User;

/* @param <T> the domain type the repository manages
  * @param <ID> the type of the id of the entity the repository manages */

@Repository  //наготово получаваме всички create, read, update, delete операции наготово
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
