package reactive;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class TransformationProcessor<T, R> implements Processor<T, R> {
    private final Function<T, R> transformation;
    private final SubmissionPublisher<R> submissionPublisher;
    private Subscription subscription;

    //нещо като pipe - ще получаваме от нашия publisher едни елементи, и ще ги превръщаме в други елементи
    public TransformationProcessor(Function<T, R> transformation) {
        this.transformation = transformation;
        this.submissionPublisher = new SubmissionPublisher<R>();
    }


    //publisher
    @Override
    public void subscribe(Subscriber<? super R> subscriber) {
        submissionPublisher.subscribe(subscriber);
    }

    //publisher
    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1); //the backpressure
    }

    //Subscriber/producer
    @Override
    public void onNext(T item) {
        R transformed = transformation.apply(item);
        submissionPublisher.submit(transformed); //събмитваме надолу по веригата
        this.subscription.request(1); //the backpressure
    }

    //Subscriber/producer
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    //Subscriber/producer
    @Override
    public void onComplete() {
        System.out.println("Transformation is complete, closing down.");
    }
}
