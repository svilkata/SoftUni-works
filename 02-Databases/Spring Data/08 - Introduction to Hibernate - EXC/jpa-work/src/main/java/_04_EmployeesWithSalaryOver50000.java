import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class _04_EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        List<String> resultList = entityManager.createQuery("SELECT e.firstName FROM Employee e" +
                        " WHERE e.salary > 50000",
                String.class)
                .getResultList();

        String res = String.join("\n", resultList);
        System.out.println(res);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
