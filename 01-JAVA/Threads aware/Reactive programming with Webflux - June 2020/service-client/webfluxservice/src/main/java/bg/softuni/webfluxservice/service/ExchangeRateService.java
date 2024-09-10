package bg.softuni.webfluxservice.service;

import bg.softuni.webfluxservice.model.ExchangeRate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ExchangeRateService {

    private List<ExchangeRate> exchangeRates = new ArrayList<>();

    public ExchangeRateService() {
        exchangeRates.add(new ExchangeRate("EUR", "BGN", Instant.now(), BigDecimal.valueOf(1.96)));
        exchangeRates.add(new ExchangeRate("USD", "BGN", Instant.now(), BigDecimal.valueOf(1.74)));
        exchangeRates.add(new ExchangeRate("GBN", "BGN", Instant.now(), BigDecimal.valueOf(2.16)));
        exchangeRates.add(new ExchangeRate("RSD", "BGN", Instant.now(), BigDecimal.valueOf(0.017)));
    }

    public Flux<ExchangeRate> getExchangeRateStream(int durationInterval) {
        Flux<ExchangeRate> ret = Flux.generate(() -> 0,
                (index, sink) -> {
                    ExchangeRate updateRate = randomize(exchangeRates.get(index));
                    sink.next(updateRate);
                    System.out.println("Generated a new exchange rate...");

                    return (++index) % exchangeRates.size();
                });

        if (durationInterval > 0) {
            return ret.delayElements(Duration.ofSeconds(durationInterval));
        } else {
            return ret;
        }
    }

    private ExchangeRate randomize(ExchangeRate initial) {
        return new ExchangeRate(
                initial.getFromCurrency(),
                initial.getToCurrency(),
                Instant.now(),
                randomize(initial.getRate())
        );
    }

    private BigDecimal randomize(BigDecimal initialRate) {
        Random random = new Random();
        double deviation = initialRate.doubleValue() *
                random.nextDouble() * 0.1 * (random.nextDouble() > 0.5 ? -1 : 1);

        return BigDecimal.valueOf(initialRate.doubleValue() + deviation);
    }
}
