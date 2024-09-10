package bg.softuni.webfluxclient.web;

import bg.softuni.webfluxclient.model.ExchangeRate;
import bg.softuni.webfluxclient.service.RatesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//with controller
@RestController
@RequestMapping("/ratescontroller")
public class RatesController {

    private final RatesService ratesService;

    public RatesController(RatesService ratesService) {
        this.ratesService = ratesService;
    }

    @GetMapping
    public Flux<ExchangeRate> getRates() {
        return ratesService.listRatesFromService();
    }

    @GetMapping("/{id}")
    public Mono<ExchangeRate> getRateById(@PathVariable String id) {
        return ratesService.getRateByIdFromService(id);
    }

    @PostMapping
    public Mono<ExchangeRate> createRate(@RequestBody ExchangeRate exchangeRate) {
        return ratesService.createRateFromService(exchangeRate);
    }
}
