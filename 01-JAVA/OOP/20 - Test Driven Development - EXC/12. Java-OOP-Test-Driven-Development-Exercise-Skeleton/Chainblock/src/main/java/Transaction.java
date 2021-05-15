public interface Transaction extends Comparable<Transaction>{
    int getID();
    TransactionStatus getStatus();

    void setStatus(TransactionStatus newStatus);
    double getAmount();
    String getFrom();
    String getTo();
}
