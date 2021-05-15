import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {
    private Map<Integer, Transaction> transactions;

    public ChainblockImpl() {
        this.transactions = new HashMap<>();
    }

    public int getCount() {
        return this.transactions.size();
    }

    public void add(Transaction transaction) {
        this.transactions.put(transaction.getID(), transaction);

    }

    public boolean contains(Transaction transaction) {
        return this.contains(transaction.getID());
    }

    public boolean contains(int id) {
        return this.transactions.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        validateID(id);
        this.transactions.get(id).setStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        validateID(id);
        this.transactions.remove(id);

    }

    public Transaction getById(int id) {
        validateID(id);
        return this.transactions.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        return collectNonEmptyRequiredListData(status).stream()
                .sorted((f, s) -> Double.compare(s.getAmount(), f.getAmount()))
                .collect(Collectors.toList());
    }

    private List<Transaction> collectNonEmptyRequiredListData(TransactionStatus status) {
        List<Transaction> result = this.transactions.values()
                .stream()
                .filter(t -> t.getStatus().equals(status))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        return this.collectNonEmptyRequiredListData(status)
                .stream()
                .sorted((f, s) -> Double.compare(s.getAmount(), f.getAmount()))
                .map(t -> t.getFrom())
                .collect(Collectors.toList());
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        return null;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return null;
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        return null;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        return null;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return null;
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        return null;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        return null;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return null;
    }

    public Iterator<Transaction> iterator() {
        return null;
    }

    private void validateID(int id) {
        if (!this.contains(id)) {
            throw new IllegalArgumentException();
        }
    }
}
