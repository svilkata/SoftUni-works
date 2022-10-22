package reactive;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

class ReactiveTests {

    @Test
    void testAllItemsConsumed() {
        //The producer!!!
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        SimpleSubscriber<String> mySubscriber = new SimpleSubscriber<>();

        //set new consumer for producer publisher
        publisher.subscribe(mySubscriber);

        assertEquals(1, publisher.getNumberOfSubscribers()); //Returns the number of current subscribers.

        List<String> names = List.of("Anna", "John", "Pesho");
        names.forEach(item -> publisher.submit(item));  //sbmit = publishes the given items to each current subscriber
        publisher.close();

        //Make it synchronous with the awaitility library
        await()
                .atMost(1, TimeUnit.SECONDS)
                .until(
                        () -> mySubscriber.getConsumedElementsCount() == names.size()
                );

        assertEquals(3, mySubscriber.getConsumedElementsCount());
    }

    @Test
    public void testTransformation() {
        //arrange = given part
        Function<String, String> transfFunc = String::toUpperCase;
        TransformationProcessor<String, String> transformationPipe = new TransformationProcessor<>(transfFunc);

        SubmissionPublisher<String> startPublisher = new SubmissionPublisher<>();
        SimpleSubscriber<String> finishSubscriber = new SimpleSubscriber<>();

        List<String> items = List.of("SenKo", "Pesho", "lilly"); //от едната страна на pipe-а
        List<String> expectedItems = List.of("SENKO", "PESHO", "LILLY"); //какво излизза от другата страна на pipe-а


        //act = when
        startPublisher.subscribe(transformationPipe); //началната точка на pipe-a
        transformationPipe.subscribe(finishSubscriber); //крайната точка на Pipe-a

        items.forEach(item -> startPublisher.submit(item));
        startPublisher.close();

        //Make it synchronous with the awaitility library
        await()
                .atMost(1, TimeUnit.SECONDS)
                .until(
                        () -> expectedItems.equals(finishSubscriber.getConsumedElements())
                );

        //assert
        assertEquals(expectedItems, finishSubscriber.getConsumedElements());
    }
}