package _02_SalesDatabase;


import _02_SalesDatabase.entities.Customer;
import _02_SalesDatabase.entities.Product;
import _02_SalesDatabase.entities.Sale;
import _02_SalesDatabase.entities.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class _02Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_relations_exc");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Product product = new Product("asd", 123, BigDecimal.TEN);
        Customer customer = new Customer("Svilen", "svill@abv.bg", "myCreditCard");
        StoreLocation location = new StoreLocation("Sofia store Lyulin");
        Sale sale = new Sale(product, customer, location);

        entityManager.persist(product);
        entityManager.persist(customer);
        entityManager.persist(location);
        entityManager.persist(sale);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
