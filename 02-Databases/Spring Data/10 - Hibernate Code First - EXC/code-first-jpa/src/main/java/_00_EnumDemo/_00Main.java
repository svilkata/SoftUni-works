package _00_EnumDemo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _00Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_relations_exc");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        User user = new User("Marina", AccountType.GOLD);
        entityManager.persist(user);

        User found = entityManager.find(User.class, 1);
        System.out.println(found.getType().toString());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
