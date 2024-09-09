package reactive;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.Flow.Subscriber;

//I.e. this is the consumer
public class SimpleSubscriber<T> implements Subscriber<T> {
    private List<T> consumedElements = new LinkedList<>();
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        consumedElements.add(item);

        //Applying backpressure - искам вече следващото
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("I'm complete!!!");
        consumedElements.forEach(System.out::println);
        System.out.println("--------------");
    }

    public int getConsumedElementsCount(){
        return consumedElements.size();
    }

    public List<T> getConsumedElements() {
        return consumedElements;
    }
}
