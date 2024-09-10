package bg.softuni.webfluxservice.web;

import bg.softuni.webfluxservice.service.ExchangeRateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;


@Configuration
public class ExchangeRateRouter {

    @Bean
    public RouterFunction<ServerResponse> route(ExchangeRateHandler exchangeRateHandler) {
        return RouterFunctions
                .route(GET("/rates").and(accept(MediaType.APPLICATION_STREAM_JSON)), sr -> exchangeRateHandler.streamExchangeRates(sr));
//                .andRoute(GET("/rates").and(accept(MediaType.APPLICATION_JSON)), exchangeRateHandler::getExchangeRates);
    }
}
