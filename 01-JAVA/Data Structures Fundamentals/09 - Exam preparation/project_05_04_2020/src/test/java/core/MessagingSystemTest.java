package core;

import model.Message;
import model.TextMessage;
import org.junit.Before;
import org.junit.Test;
import shared.DataTransferSystem;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MessagingSystemTest {
    private List<Message> messages;

    private DataTransferSystem system;

    @Before
    public void setUp() {
        this.messages = List.of(
                new TextMessage(11, "test_text"),
                new TextMessage(6, "test_text"),
                new TextMessage(19, "test_text"),
                new TextMessage(4, "test_text"),
                new TextMessage(8, "test_text"),
                new TextMessage(17, "test_text")
        );

        this.system = new MessagingSystem();

        assertEquals(0, this.system.size());

        for (Message message : this.messages) {
            this.system.add(message);
        }
    }

    @Test
    public void testAdd() {
        MessagingSystem ms = new MessagingSystem();
        ms.add(new TextMessage(13, "Hello"));
        ms.add(new TextMessage(7, "Hello"));
        ms.add(new TextMessage(8, "Hello"));
        ms.add(new TextMessage(26, "Hello"));
        ms.add(new TextMessage(14, "Hello"));

        assertEquals(5, ms.size);
        assertEquals(13, ms.root.getWeight());
        assertEquals(7, ms.root.left.getWeight());
        assertEquals(8, ms.root.left.right.getWeight());
        assertEquals(26, ms.root.right.getWeight());
        assertEquals(14, ms.root.right.left.getWeight());
    }

    @Test
    public void testGetByWeight() {
        MessagingSystem ms = new MessagingSystem();
        ms.add(new TextMessage(13, "Hello"));
        ms.add(new TextMessage(7, "Hello"));
        ms.add(new TextMessage(8, "Hello"));
        ms.add(new TextMessage(26, "Hello"));
        ms.add(new TextMessage(14, "Hello"));
        Message result = ms.getByWeight(8);

        assertEquals(8, result.getWeight());
    }

    @Test
    public void testGetOrderedByWeight() {
        MessagingSystem ms = new MessagingSystem();
        ms.add(new TextMessage(13, "Hello"));
        ms.add(new TextMessage(7, "Hello"));
        ms.add(new TextMessage(8, "Hello"));
        ms.add(new TextMessage(26, "Hello"));
        ms.add(new TextMessage(14, "Hello"));
        List<Message> orderedByWeight = ms.getOrderedByWeight();

        assertEquals(7, orderedByWeight.get(0).getWeight());
        assertEquals(8, orderedByWeight.get(1).getWeight());
        assertEquals(13, orderedByWeight.get(2).getWeight());
    }

    @Test
    public void testAddSingleShouldWorkCorrectly() {
        DataTransferSystem system = new MessagingSystem();

        assertEquals(0, system.size());

        system.add(new TextMessage(12, "test_text"));

        assertEquals(1, system.size());
    }

    @Test
    public void testAddMultipleShouldWorkCorrectly() {
        DataTransferSystem system = new MessagingSystem();

        assertEquals(0, system.size());

        for (Message message : messages) {
            system.add(message);
        }

        assertEquals(messages.size(), system.size());
    }

    @Test
    public void testAddMultipleShouldSetCorrectElements() {
        assertEquals(messages.size(), system.size());
        Message lightest = system.getLightest();
        assertNotNull(lightest);
        assertEquals(4, lightest.getWeight());
        Message heaviest = system.getHeaviest();
        assertNotNull(heaviest);
        assertEquals(19, heaviest.getWeight());
    }

    @Test
    public void testGetPostOrderShouldReturnCorrectSequence() {
        List<Message> postOrder = this.system.getPostOrder();

        int[] expected = {4, 8, 6, 17, 19, 11};
        assertNotNull(postOrder);
        assertEquals(expected.length, postOrder.size());

        for (int i = 0; i < messages.size(); i++) {
            assertEquals(expected[i], postOrder.get(i).getWeight());
        }
    }

    @Test
    public void testGetLightestShouldReturnCorrect() {
        Message lightest = this.system.getLightest();
        assertNotNull(lightest);
        assertEquals(4, lightest.getWeight());
    }

    @Test
    public void testGetHeaviestShouldReturnCorrect() {
        Message heaviest = this.system.getHeaviest();
        assertNotNull(heaviest);
        assertEquals(19, heaviest.getWeight());
    }
}