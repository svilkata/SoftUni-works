package bg.softuni.webfluxclient.web;

import bg.softuni.webfluxclient.model.ExchangeRate;
import bg.softuni.webfluxclient.service.RatesService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    //when more than 1 element, we return flux
    @GetMapping
    public Flux<ExchangeRate> getRates() {
        return ratesService.listRatesFromService();
    }

    //returning 1 element
    @GetMapping("/{id}")
    public Mono<ExchangeRate> getRateById(@PathVariable String id) {
        return ratesService.getRateByIdFromService(id);
    }

    //returning 1 element - the created one
    @PostMapping
    public Mono<ExchangeRate> createRate(@RequestBody ExchangeRate exchangeRate) {
        return ratesService.createRateFromService(exchangeRate);
    }

    //returning 1 element - the updated one
    @PatchMapping("{id}")
    public Mono<ExchangeRate> updateRate(@PathVariable String id, @RequestBody ExchangeRate exchangeRate) {
        return ratesService.updateRateFromService(id, exchangeRate);
    }

    //when no element to return, we use void
    @DeleteMapping("{id}")
    public Mono<Void> updateRate(@PathVariable String id) {
        return ratesService.deleteRateFromService(id);
    }
}
