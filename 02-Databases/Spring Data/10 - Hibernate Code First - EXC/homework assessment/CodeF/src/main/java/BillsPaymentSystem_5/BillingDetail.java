package BillsPaymentSystem_5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "billing_details")
public abstract class BillingDetail extends BaseEntity {

    private String number;
    private User_5 owner;

    @Column(nullable = false, unique = true)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ManyToOne()
    public User_5 getOwner() {
        return owner;
    }

    public void setOwner(User_5 owner) {
        this.owner = owner;
    }
}
