package bg.softuni.webfluxclient.service;

import bg.softuni.webfluxclient.model.ExchangeRate;
import bg.softuni.webfluxclient.repository.ExchangeRateRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class RatesHandler {
    private final ExchangeRateRepository userReactiveRepository;

    public RatesHandler(ExchangeRateRepository userReactiveRepository) {
        this.userReactiveRepository = userReactiveRepository;
    }

    public Mono<ServerResponse> listRatesFromHandler() {
        return ok().body(userReactiveRepository.findAll(), ExchangeRate.class);
    }

    public Mono<ServerResponse> getRateByIdFromHandler(ServerRequest request) {
        String rateId = request.pathVariable("id");
        return userReactiveRepository.findById(rateId)
                .flatMap(rate -> ok().contentType(APPLICATION_JSON).bodyValue(rate))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
