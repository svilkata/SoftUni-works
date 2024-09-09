package bg.softuni.fluxmono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class AsyncFlux {

    //синхронно
    @Test
    public void syncFlux() {
        MyInteger sum = new MyInteger(0);

//        Create a Flux that emits the provided elements and then completes.
        Flux.just(1, 2, 3, 4)
                .reduce(MyInteger::sum)
                .subscribe(sum::set);

        System.out.println(sum);

//        Output
//        Summing 1 and 2
//        Summing 3 and 3
//        Summing 6 and 4
//        MyInteger{initialValue=10}
    }


    //асинхронно
    @Test
    public void аsyncFlux() {
        MyInteger sum = new MyInteger(0);

        Flux.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.boundedElastic())
                .reduce(MyInteger::sum)
                .subscribe(sum::set);

        System.out.println(sum);

        //Output
//        MyInteger{initialValue=0}
//        Summing 1 and 2
//        Summing 3 and 3
//        Summing 6 and 4
    }

}


class MyInteger {
    private Integer initialValue;

    public MyInteger(Integer initialValue) {
        this.initialValue = initialValue;
    }

    public static int sum(int a, int b) {
        System.out.println("Summing " + a + " and " + b);
        return a + b;
    }

    public void set(Integer aNumber) {
        this.initialValue = aNumber;
    }

    @Override
    public String toString() {
        return "MyInteger{" +
                "initialValue=" + initialValue +
                '}';
    }
}
