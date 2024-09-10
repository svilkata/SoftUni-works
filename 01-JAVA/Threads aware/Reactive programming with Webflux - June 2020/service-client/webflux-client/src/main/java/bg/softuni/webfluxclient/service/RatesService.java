package bg.softuni.webfluxclient.service;

import bg.softuni.webfluxclient.model.ExchangeRate;
import bg.softuni.webfluxclient.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RatesService {
    private final ExchangeRateRepository rateReactiveRepository;

    public RatesService(ExchangeRateRepository rateReactiveRepository) {
        this.rateReactiveRepository = rateReactiveRepository;
    }

    public Flux<ExchangeRate> listRatesFromService() {
        return rateReactiveRepository.findAll();
    }

    public Mono<ExchangeRate> getRateByIdFromService(String id) {
        return rateReactiveRepository.findById(id);
    }

    public Mono<ExchangeRate> createRateFromService(ExchangeRate exchangeRate) {
        return rateReactiveRepository.save(exchangeRate);
    }

    public Mono<ExchangeRate> updateRateFromService(String id, ExchangeRate exchangeRate) {
        return rateReactiveRepository.findById(id)
                .map(r -> {
                    r.setRate(exchangeRate.getRate());
                    r.setFromCurrency(exchangeRate.getFromCurrency());
                    r.setToCurrency(exchangeRate.getToCurrency());
                    r.setTime(exchangeRate.getTime());

                    return r;
                })
                .flatMap(rateReactiveRepository::save);
    }

    public Mono<Void> deleteRateFromService(String id) {
        return rateReactiveRepository.deleteById(id);
    }
}
