import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class _12EmplMaxSal {


    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        //Използване на реална JOIN заявка
        List<String> resultList = entityManager.createQuery("SELECT CONCAT(d.name, ' ', MAX(e.salary)) FROM Employee AS e" +
                        " JOIN e.department AS d" +
                        " GROUP BY d.id" +
                        " HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000",
                String.class)
                .getResultList();

        for (String s : resultList) {
            System.out.printf("%s%n", s);
        }

        /*
        String query = "SELECT e.department.name, MAX(e.salary) FROM Employee e" +
                "  GROUP BY e.department" +
                " HAVING MAX(e.salary) NOT BETWEEN :min AND :max";
        TypedQuery<CustomEmployee> typQuery = entityManager.createQuery(query, CustomEmployee.class);

        typQuery.setParameter("min", 30000);
        typQuery.setParameter("max", 70000);
        List<CustomEmployee> resultList1 = typQuery.getResultList();

        for (CustomEmployee customEmployee : resultList1) {
            System.out.println(String.format("%s %.2f", customEmployee.department.getName(), customEmployee.salary));
        }*/

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
