package bg.softuni.webfluxclient.service;

import bg.softuni.webfluxclient.model.ExchangeRate;
import bg.softuni.webfluxclient.model.ExchangeRateClient;
import bg.softuni.webfluxclient.repository.ExchangeRateRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExchangeRateInit implements ApplicationListener<ContextRefreshedEvent> {

    private final ExchangeRateClient client;
    private final ExchangeRateRepository repository;

    public ExchangeRateInit(ExchangeRateClient client, ExchangeRateRepository repository) {
        this.client = client;
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        client.getRateStream()
                .subscribe(exchangeRate -> {
                    Mono<ExchangeRate> exchangeRateMono = repository.save(exchangeRate);
                    exchangeRateMono.subscribe(er -> System.out.println("Saved " + er));
                });
    }
}
