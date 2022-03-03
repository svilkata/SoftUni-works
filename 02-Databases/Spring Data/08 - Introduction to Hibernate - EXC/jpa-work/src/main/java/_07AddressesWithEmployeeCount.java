import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _07AddressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner sc = new Scanner(System.in);

        entityManager.createQuery("FROM Address a" +
                " ORDER BY a.employees.size DESC",             //вместо COUNT(a.employees)
                Address.class)
                .setMaxResults(10)                      //вместо LIMIT 10
                .getResultStream()
                .forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
