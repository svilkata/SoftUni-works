package bg.softuni.webfluxclient.service;

import bg.softuni.webfluxclient.model.ExchangeRate;
import bg.softuni.webfluxclient.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RatesService {
    private final ExchangeRateRepository userReactiveRepository;

    public RatesService(ExchangeRateRepository userReactiveRepository) {
        this.userReactiveRepository = userReactiveRepository;
    }

    public Flux<ExchangeRate> listRatesFromService() {
        return userReactiveRepository.findAll();
    }

    public Mono<ExchangeRate> getRateByIdFromService(String id) {
        return userReactiveRepository.findById(id);
    }

    public Mono<ExchangeRate> createRateFromService(ExchangeRate exchangeRate) {
    }
}
