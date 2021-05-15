public class TransactionImpl implements Transaction {

    private int id;
    private TransactionStatus status;
    private String from;
    private String to;
    private double amount;

    public TransactionImpl(int id, TransactionStatus status, String from, String to, double amount) {
        this.id = id;
        this.status = status;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return this.from;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.id) * 17;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TransactionImpl)) { //object инстанция на класа TransactionImpl ли е?
            return false; //ако не е, то върни false
        }

        TransactionImpl other = (TransactionImpl) obj;

        return this.hashCode() == other.hashCode();
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public TransactionStatus getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(TransactionStatus newStatus) {
        this.status = newStatus;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }

    @Override
    public String getFrom() {
        return this.from;
    }

    @Override
    public String getTo() {
        return this.to;
    }


    @Override
    public int compareTo(Transaction other) {
        return this.from.compareTo(other.getFrom());
    }
}
