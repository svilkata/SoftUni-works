package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Agent;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    @Query("SELECT a FROM Agent a" +
            " WHERE a.firstName =:name OR a.lastName =:name")
    Optional<Agent> findByFirstNameOrLastName(String name);

//    Optional<Agent> findByFirstNameAndLastName(String firstName, String lastName);
}
