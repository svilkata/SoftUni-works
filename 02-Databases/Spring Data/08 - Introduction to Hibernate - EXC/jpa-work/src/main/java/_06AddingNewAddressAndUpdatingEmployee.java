import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _06AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner sc = new Scanner(System.in);
        String lastName = sc.nextLine();

        String addressText = "Vitoshka 15";
        Address address = new Address();
        address.setText(addressText);

        entityManager.persist(address);

        /*
        Employee employee = entityManager.createQuery("SELECT e FROM Employee e" +
                        " WHERE e.lastName = :employeeName",
                Employee.class)
                .setParameter("employeeName", lastName)
                .getSingleResult();

        employee.setAddress(address);
        entityManager.persist(employee);
        */

        //без да type-cast-ваме
        entityManager.createQuery("UPDATE Employee e" +
                        " SET e.address = :addr" +
                        " WHERE e.lastName = :employeeName")
                .setParameter("employeeName", lastName)
                .setParameter("addr", address)
                .executeUpdate();


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
