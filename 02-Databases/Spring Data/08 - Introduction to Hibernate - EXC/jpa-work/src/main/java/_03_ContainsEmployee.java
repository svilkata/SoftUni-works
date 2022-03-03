import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Scanner;

public class _03_ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Scanner sc = new Scanner(System.in);
        String[] searchFor = sc.nextLine().split(" ");

/*
        CriteriaBuilder builder = entityManager.getCriteriaBuilder(); //by CriteriaBuilder it is type-safe
        CriteriaQuery criteria = builder.createQuery();
        Root<Town> r = criteria.from(Town.class);
        criteria.select(r).where(builder.);*/


        Long employeeCount = entityManager.createQuery("SELECT COUNT(e) FROM Employee e" +
                        " WHERE e.firstName = :first_name" +
                        " AND e.lastName = :last_name",
                Long.class)
                .setParameter("first_name", searchFor[0])
                .setParameter("last_name", searchFor[1])
                .getSingleResult();

        if (employeeCount > 0L) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
