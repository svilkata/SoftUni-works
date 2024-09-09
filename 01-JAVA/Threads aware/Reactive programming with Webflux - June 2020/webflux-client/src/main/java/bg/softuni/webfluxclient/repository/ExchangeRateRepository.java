package bg.softuni.webfluxclient.repository;

import bg.softuni.webfluxclient.model.ExchangeRate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends ReactiveMongoRepository<ExchangeRate, String> {

}
