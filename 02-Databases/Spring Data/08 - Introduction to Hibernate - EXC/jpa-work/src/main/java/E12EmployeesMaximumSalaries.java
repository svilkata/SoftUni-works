import entities.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Comparator;

public class E12EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        em.createQuery("FROM Department d", Department.class)
                .getResultStream()
                .map(Department::getEmployees)
                .map(e -> e.stream()
                        .max(Comparator.comparingDouble(d -> d.getSalary().doubleValue()))
                        .orElseThrow(IllegalStateException::new))
                .filter(e -> e.getSalary().compareTo(new BigDecimal("30000")) < 0 || e.getSalary().compareTo(new BigDecimal("70000")) > 0)
                .forEach(e -> System.out.printf("%s %.2f%n", e.getDepartment().getName(), e.getSalary()));

    }
}