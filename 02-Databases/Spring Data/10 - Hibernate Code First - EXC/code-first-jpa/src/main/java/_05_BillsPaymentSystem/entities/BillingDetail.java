package _05_BillsPaymentSystem.entities;

import javax.persistence.*;

//@MappedSuperclass //нещо междинно го анотира
@Entity
@Table(name = "_05_billing_details")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(nullable = false, unique = true)
    private String number;

    @ManyToOne
    private User owner;

    protected BillingDetail() {
    }

    protected BillingDetail(String number, User owner) {
        this.number = number;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
