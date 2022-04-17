package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Agent;

import java.util.Optional;


public interface AgentRepository extends JpaRepository<Agent,Long> {

    Optional<Agent> findByFirstName(String firstName);
}
