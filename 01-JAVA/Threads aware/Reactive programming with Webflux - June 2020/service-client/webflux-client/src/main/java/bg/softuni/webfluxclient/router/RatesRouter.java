package bg.softuni.webfluxclient.router;

import bg.softuni.webfluxclient.handler.RatesHandler;
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
                .POST("ratesrouter", accept(APPLICATION_JSON), ratesHandler::createRateFromHandler)
                .PATCH("ratesrouter/{id}", accept(APPLICATION_JSON), request -> ratesHandler.updateRateFromHandler(request))
                .DELETE("ratesrouter/{id}", serverRequest -> ratesHandler.deleteRateFromHandler(serverRequest))
                .build();
    }
}