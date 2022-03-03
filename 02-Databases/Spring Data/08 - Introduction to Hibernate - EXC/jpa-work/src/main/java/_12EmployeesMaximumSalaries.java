import entities.CustomEmployee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class _12EmployeesMaximumSalaries {


    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

//        SELECT e.department_id, MAX(e.`salary`) FROM employees AS e
//        GROUP BY e.`department_id`
//        HAVING MAX(e.`salary`) NOT BETWEEN 30000 AND 70000;

        List<Object[]> resultList = entityManager.createQuery("SELECT e.department.name, MAX(e.salary) FROM Employee e" +
                        "  GROUP BY e.department.id" +
                        " HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000",
                Object[].class)
                .getResultList();

        for (Object[] o : resultList) {
            String departName = (String) o[0];
            BigDecimal salary = (BigDecimal) o[1];
            System.out.printf("%s %.2f%n", departName, salary);
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
