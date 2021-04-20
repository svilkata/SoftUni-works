import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ChainblockImplTest {
    private Chainblock chainblock;
    private static final Transaction TRANSACTION = new TransactionImpl(
            5,
            TransactionStatus.SUCCESSFUL,
            "Marti",
            "SoftUni",
            6000.5);

    @Before
    public void setUP() {
        this.chainblock = new ChainblockImpl();
    }

    @Test
    public void afterAddingTransactionChainblockSizeShouldIncreaseWithOne() {
        this.chainblock.add(TRANSACTION);
        int count = this.chainblock.getCount();

        assertEquals(1, count);
    }

    @Test
    public void afterAddingTransactionChainblockShouldContainIt() {
        this.chainblock.add(TRANSACTION);

        assertTrue(this.chainblock.contains(TRANSACTION.getID()));
    }

    @Test
    public void containsShouldReturnTrueWhenCalledByTransactionParameter() {
        this.chainblock.add(TRANSACTION);

        assertTrue(this.chainblock.contains(TRANSACTION));
    }

    @Test
    public void changingTransactionStatusShouldApplyNewStatus() {
        this.chainblock.add(TRANSACTION);
        TransactionStatus newStatus = this.getNextStatusValue(TRANSACTION);

        this.chainblock.changeTransactionStatus(TRANSACTION.getID(), newStatus);

        assertEquals(newStatus, TRANSACTION.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeTransactionStatusShouldThrowInvalidTransactionID() {
        this.chainblock.add(TRANSACTION);
        this.chainblock.changeTransactionStatus(TRANSACTION.getID() + 1, TransactionStatus.UNAUTHORIZED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeTransactionByIdShouldThrowInvalidTransactionID() {
        this.chainblock.add(TRANSACTION);
        this.chainblock.removeTransactionById(TRANSACTION.getID() + 1);
    }

    @Test
    public void removeTransactionByIDShouldRemoveTheEntity() {
        this.chainblock.add(TRANSACTION);
        this.chainblock.removeTransactionById(TRANSACTION.getID());
        assertFalse(this.chainblock.contains(TRANSACTION));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByIDShouldThrowInvalidIDWhenEmpty() {
        this.chainblock.getById(TRANSACTION.getID());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByIDShouldThrowInvalidID() {
        this.chainblock.add(TRANSACTION);
        this.chainblock.getById(TRANSACTION.getID() + 1);
    }

    @Test
    public void getByIDShouldReturnTheSameTransaction() {
        this.chainblock.add(TRANSACTION);
        assertEquals(TRANSACTION, this.chainblock.getById(TRANSACTION.getID()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByTransactionStatusShouldThrowWhenNoTransactionsWithStatusParamArePresent() {
        this.chainblock.add(TRANSACTION);
        TransactionStatus status = this.getNextStatusValue(TRANSACTION);
        this.chainblock.getByTransactionStatus(status);
    }

    private TransactionStatus getNextStatusValue(Transaction transaction) {
        int ordinalNextStatusInEnum = (TRANSACTION.getStatus().ordinal() + 1) % TransactionStatus.values().length;
        return TransactionStatus.values()[ordinalNextStatusInEnum];
    }

    @Test
    public void getByTransactionStatusShouldReturnTheCorrectCountOfTransactions() {
        int[] statusCounter = new int[TransactionStatus.values().length];
        fillWithRandomTransactions(15, statusCounter);
        this.chainblock.add(TRANSACTION);
        Iterable<Transaction> byTransactionStatus = this.chainblock.getByTransactionStatus(TRANSACTION.getStatus());

        int counter = 0;
        for (Transaction trs : byTransactionStatus) {
            if (trs.getStatus().equals(TRANSACTION.getStatus())) {
                counter++;
            }
        }

        int actual = statusCounter[TRANSACTION.getStatus().ordinal()];
        assertEquals(actual, counter);
    }

    @Test
    public void getByTransactionStatusShouldReturnTheTransactionsInTheRightDecreasingOrder() {
        int[] statusCounter = new int[TransactionStatus.values().length];
        fillWithRandomTransactions(15, statusCounter);
        this.chainblock.add(TRANSACTION);
        Iterable<Transaction> byTransactionStatus = this.chainblock.getByTransactionStatus(TRANSACTION.getStatus());

        List<Double> amounts = new ArrayList<>();

        for (Transaction trs : byTransactionStatus) {
            amounts.add(trs.getAmount());
        }

        List<Double> sorted = amounts.stream()
                .sorted(Double::compareTo)
                .collect(Collectors.toList());

        Collections.reverse(sorted);

        assertEquals(sorted, amounts);
    }

    private void fillWithRandomTransactions(int count, int[] statusCounter) {
        TransactionStatus[] statuses = TransactionStatus.values();

        for (int i = 1; i <= count; i++) {
            int id = i;
            int index = i % statuses.length;
            TransactionStatus status = statuses[index];
            if (statusCounter != null) {
                statusCounter[index]++;
            }
            String from = "From" + (char) ThreadLocalRandom.current().nextInt(48, 125);
            String to = "To" + (char) ThreadLocalRandom.current().nextInt(48, 125);
            double amount = i * 5.127 + ThreadLocalRandom.current().nextDouble(100, 10000);

            this.chainblock.add(new TransactionImpl(id, status, from, to, amount));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllSendersWithTransactionStatusShouldThrowWhenTransactionStatusIsAbsent() {
        this.chainblock.add(TRANSACTION);
        TransactionStatus status = this.getNextStatusValue(TRANSACTION);
        this.chainblock.getAllSendersWithTransactionStatus(status);
    }

    @Test
    public void getAllSendersWithTransactionStatusShouldReturnCorrectResult() {
        fillWithRandomTransactions(15, null);
//        this.chainblock.add(TRANSACTION);
        Iterable<String> senders = this.chainblock.getAllSendersWithTransactionStatus(TRANSACTION.getStatus());

        Iterable<Transaction> transactions = this.chainblock.getByTransactionStatus(TRANSACTION.getStatus());

        List<String> orderedSenders = getSortedListOfStringsOrObjects(senders);

        List<String> orderedTransactions = getSortedListOfStringsOrObjects(transactions);

        assertEquals(orderedTransactions, orderedSenders);
    }

    private <T extends Comparable> List<String> getSortedListOfStringsOrObjects(Iterable<T> iterable) {
        List<String> ordered = new ArrayList<>();

        for (T elem : iterable) {
            ordered.add(elem.toString());
        }
        Collections.sort(ordered);

        return ordered;
    }

    @Test
    public void getAllSendersWithTransactionStatusShouldReturnCorrectResultSorted() {
        fillWithRandomTransactions(15, null);

        Iterable<String> senders = this.chainblock.getAllSendersWithTransactionStatus(TRANSACTION.getStatus());
        List<String> aggregatedSenders = new ArrayList<>();
        Iterator<String> iter = senders.iterator();
        while (iter.hasNext()) {
            aggregatedSenders.add(iter.next());
        }

        Iterable<Transaction> transactions = this.chainblock.getByTransactionStatus(TRANSACTION.getStatus());
        List<String> orderedTransactions = new ArrayList<>();
        Iterator<Transaction> iterTwo = transactions.iterator();
        while (iterTwo.hasNext()) {
            orderedTransactions.add(iterTwo.next().getFrom());
        }

        assertEquals(orderedTransactions, aggregatedSenders);
    }
}