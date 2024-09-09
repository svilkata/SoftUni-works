package bg.softuni.fluxmono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.stream.Collectors;

class WebfluxGeneralTest {
    @Test
    public void fluxToStream() {
        Flux<String> springProjectsFlux = Flux.just(getSpringProjects());

        springProjectsFlux
                .toStream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    @Test
    public void subscribeToFlux() {
        Flux<String> springProjectsFlux = Flux.just(getSpringProjects());

        springProjectsFlux.subscribe(
                System.out::println
        );
    }

    @Test
    public void doOnEach() {
        Flux<String> springProjectsFlux = Flux.just(getSpringProjects());

        springProjectsFlux.doOnEach(
                        signalConsumer -> {
                            if (signalConsumer.isOnNext()) {
                                System.out.println(signalConsumer.get());
                            }
                        }
                )
                .subscribe();
    }

    @Test
    public void mapAndFilter() {
        Flux<String> springProjectsFlux = Flux.just(getSpringProjects());

        springProjectsFlux
                .map(String::toUpperCase)
                .filter(s -> s.contains("REST"))
                .subscribe(System.out::println);
    }

    @Test
    public void collect() {
        Flux<String> springProjectsFlux = Flux.just(getSpringProjects());

        springProjectsFlux
                .map(String::length)
                .collect(Collectors.summarizingInt(Integer::intValue))//статистика от дължината на всички заглавия
                .subscribe(System.out::println);

//        IntSummaryStatistics{count=7, sum=85, min=10, average=12.142857, max=16}
    }

    @Test
    public void subscribe() {
        Flux<String> springProjectsFlux = Flux.just(getSpringProjects());

        Consumer<String> onNextConsumer = System.out::println;
        Consumer<Throwable> onErrorConsumer = Throwable::printStackTrace;
        Runnable onDone = () -> System.out.println("We are done!");

        springProjectsFlux.subscribe(
                onNextConsumer,
                onErrorConsumer,
                onDone
        );
    }

    @Test
    public void testOnError() {
        Flux<Integer> numbers = Flux.just("1", "two", "3")
                .map(Integer::parseInt);

        Consumer<Integer> onNextConsumer = System.out::println;
        Consumer<Throwable> onErrorConsumer = Throwable::printStackTrace;
        Runnable onDone = () -> System.out.println("We are done!");

        numbers.subscribe(
                onNextConsumer,
                onErrorConsumer,   //throws error this time
                onDone
        );
    }

    @Test
    public void mono(){
        Mono.just("TEST").map(String::toUpperCase).subscribe(System.out::println);
    }

    private String[] getSpringProjects() {
        return new String[]{
                "Spring REST",
                "Spring DATA REST",
                "Spring Batch",  //Java Batching
                "Spring MVC",
                "Spring Webflux",
                "Spring JMS",   //Java Messaging Service
                "Spring Kafka"};
    }
}