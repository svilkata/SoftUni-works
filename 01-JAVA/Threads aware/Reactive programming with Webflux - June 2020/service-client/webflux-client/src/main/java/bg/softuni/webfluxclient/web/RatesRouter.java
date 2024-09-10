package bg.softuni.webfluxclient.web;

import bg.softuni.webfluxclient.service.RatesHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


//with router
@Configuration
public class RatesRouter {

    @Bean
    public RouterFunction<ServerResponse> router(RatesHandler ratesHandler) {
        return route()
                .GET("ratesrouter", accept(APPLICATION_STREAM_JSON), request -> ratesHandler.listRatesFromHandler())
//                .GET("ratesrouter/{id}", accept(APPLICATION_JSON), request -> ratesHandler.getRateByIdFromHandler(request))
                .GET("ratesrouter/{id}", accept(APPLICATION_JSON), ratesHandler::getRateByIdFromHandler)
                .build();
    }
}
