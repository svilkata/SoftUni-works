package bg.softuni.webfluxclient.service;

import bg.softuni.webfluxclient.model.ExchangeRate;
import bg.softuni.webfluxclient.reactiveclient.ExchangeRateReactiveClient;
import bg.softuni.webfluxclient.repository.ExchangeRateRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ExchangeRateInit implements ApplicationListener<ContextRefreshedEvent> {

    private final ExchangeRateReactiveClient reactiveClient;
    private final ExchangeRateRepository repository;

    public ExchangeRateInit(ExchangeRateReactiveClient reactiveClient, ExchangeRateRepository repository) {
        this.reactiveClient = reactiveClient;
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        AtomicInteger idNum = new AtomicInteger(1);
        reactiveClient.getRateStream()
                .subscribe(exchangeRate -> {
                    Mono<ExchangeRate> exchangeRateMono = repository.save(exchangeRate.setId(String.valueOf(idNum.getAndIncrement())));
                    exchangeRateMono.subscribe(er -> System.out.println("Saved " + er));
                });
    }
}
