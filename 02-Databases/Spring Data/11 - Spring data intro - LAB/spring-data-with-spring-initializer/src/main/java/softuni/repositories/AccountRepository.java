package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
