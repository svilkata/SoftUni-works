package bg.softuni.webfluxclient.reactiveclient;

import bg.softuni.webfluxclient.config.ReactiveClientConfig;
import bg.softuni.webfluxclient.model.ExchangeRate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class ExchangeRateReactiveClient {
    private final String SERVICE_URL;
    private final String API_PATH = "/rates";

    public ExchangeRateReactiveClient(ReactiveClientConfig reactiveClientConfig) {
        SERVICE_URL = reactiveClientConfig.getSchema() + "://" + reactiveClientConfig.getHost() + ":" + reactiveClientConfig.getPort();
    }

    public Flux<ExchangeRate> getRateStream(){
        return WebClient.builder()
                .baseUrl(SERVICE_URL)
                .build()
                .get()
                .uri(API_PATH)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(ExchangeRate.class);
    }
}
