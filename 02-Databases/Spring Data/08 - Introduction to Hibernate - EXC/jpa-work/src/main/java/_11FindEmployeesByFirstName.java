import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Scanner;

public class _11FindEmployeesByFirstName {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Scanner sc = new Scanner(System.in);
        String pattern = sc.nextLine().toLowerCase();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder(); //by CriteriaBuilder it is type-safe
        CriteriaQuery criteria = builder.createQuery();
        Root<Employee> r = criteria.from(Employee.class);
        CriteriaQuery criteriaQuery = criteria.select(r).where(builder.like(r.get("firstName"),
                String.format("%s", pattern) + "%"));

        //We put the criteria query here
        List<Employee> resultList = entityManager.createQuery(criteriaQuery)
                .getResultList();

        for (Employee e : resultList) {
            String toPrint = String.format("%s %s - %s - ($%.2f)", e.getFirstName(), e.getLastName(),
                    e.getJobTitle(), e.getSalary());
            System.out.println(toPrint);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
