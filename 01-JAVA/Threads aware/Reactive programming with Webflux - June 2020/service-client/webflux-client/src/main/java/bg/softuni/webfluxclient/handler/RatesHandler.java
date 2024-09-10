package bg.softuni.webfluxclient.handler;

import bg.softuni.webfluxclient.model.ExchangeRate;
import bg.softuni.webfluxclient.repository.ExchangeRateRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class RatesHandler {
    private final ExchangeRateRepository rateReactiveRepository;

    public RatesHandler(ExchangeRateRepository rateReactiveRepository) {
        this.rateReactiveRepository = rateReactiveRepository;
    }

    public Mono<ServerResponse> listRatesFromHandler() {
        return ok().body(rateReactiveRepository.findAll(), ExchangeRate.class);
    }

    public Mono<ServerResponse> getRateByIdFromHandler(ServerRequest request) {
        String rateId = request.pathVariable("id");
        return rateReactiveRepository.findById(rateId)
                .flatMap(rate -> ok().contentType(APPLICATION_JSON).bodyValue(rate))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createRateFromHandler(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ExchangeRate.class)
                .flatMap(user -> rateReactiveRepository.save(user))
                .flatMap(savedRate -> ServerResponse
                        .created(URI.create("/ratesrouter/" + savedRate.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedRate))
                .onErrorResume(e -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> updateRateFromHandler(ServerRequest request) {
        String rateId = request.pathVariable("id");
        Mono<ExchangeRate> rateToUpdate = request.bodyToMono(ExchangeRate.class);

        return rateToUpdate
                .flatMap(exchangeRate -> rateReactiveRepository.findById(rateId)
                        .flatMap(r -> {
                            r.setRate(exchangeRate.getRate());
                            r.setFromCurrency(exchangeRate.getFromCurrency());
                            r.setToCurrency(exchangeRate.getToCurrency());
                            r.setTime(exchangeRate.getTime());

                            return rateReactiveRepository.save(r);
                        }))
                .flatMap(updatedRate -> ServerResponse.ok().bodyValue(updatedRate))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteRateFromHandler(ServerRequest request) {
        String rateId = request.pathVariable("id");

        return rateReactiveRepository.findById(rateId)
                .flatMap(existingRate -> rateReactiveRepository.delete(existingRate)
                        .then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
