import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Parameter;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class _10IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        List<String> updateCriteria = Arrays.asList("Engineering", "Tool Design", "Marketing", "Information Services");

        entityManager.createQuery("SELECT e FROM Employee e" +
                        "  WHERE e.department.name IN (:params)",
                Employee.class)
                .setParameter("params", updateCriteria)
                .getResultStream()
                .forEach(е -> е.setSalary(е.getSalary().multiply(BigDecimal.valueOf(1.12))));  //set на полета на обекти, които се оказва се записват и в базата данни


        List<Employee> resultList = entityManager.createQuery("SELECT e FROM Employee e" +
                        "  WHERE e.department.name IN (:params)",
                Employee.class)
                .setParameter("params", updateCriteria)
                .getResultList();

        resultList.stream()
                .forEach(e -> System.out.println(String.format("%s %s ($%.2f)",
                        e.getFirstName(), e.getLastName(), e.getSalary())));


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
