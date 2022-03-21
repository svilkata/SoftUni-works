package _05_BillsPaymentSystem;

import _05_BillsPaymentSystem.entities.BankAccount;
import _05_BillsPaymentSystem.entities.BillingDetail;
import _05_BillsPaymentSystem.entities.CreditCard;
import _05_BillsPaymentSystem.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _05Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_relations_exc");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        User user1 = new User("Bash", "Maistora", "mymail@abv.bg", "mypassword");
        User user2 = new User("Svilen", "Velikov", "svill@abv.bg", "mypasswordYee");

        //user 1
        BillingDetail billingDetail1 = new CreditCard("987654321", user1, "MASTERCARD",
                "08", "24");

        BillingDetail billingDetail2 = new BankAccount("111222333", user1, "OBB",
                "UBBS12D4");
        user1.addBillingDetail(billingDetail1);
        user1.addBillingDetail(billingDetail2);

        //user 2
        BillingDetail billingDetail3 = new CreditCard("12345678", user2, "VISA",
                "10", "23");
        BillingDetail billingDetail4 = new BankAccount("333222111", user2, "Bulbank",
                "BBBU12Z4");
        BillingDetail billingDetail5 = new BankAccount("999888777", user2, "Bulbank",
                "BBBU12Z4");
        user2.addBillingDetail(billingDetail3);
        user2.addBillingDetail(billingDetail4);
        user2.addBillingDetail(billingDetail5);

        entityManager.persist(user1);
        entityManager.persist(user2);

        entityManager.persist(billingDetail1);
        entityManager.persist(billingDetail2);
        entityManager.persist(billingDetail3);
        entityManager.persist(billingDetail4);
        entityManager.persist(billingDetail5);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}



