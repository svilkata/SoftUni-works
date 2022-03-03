import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _08GetEmployeeWithProject {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner sc = new Scanner(System.in);
        int emplId = Integer.parseInt(sc.nextLine());

        Employee employee = entityManager.createQuery("SELECT e FROM Employee e" +
                        " WHERE e.id= :emId",
                Employee.class)
                .setParameter("emId", emplId)
                .getSingleResult();

        System.out.println(employee);


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
