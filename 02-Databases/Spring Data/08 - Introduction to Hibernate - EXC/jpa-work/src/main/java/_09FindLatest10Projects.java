import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class _09FindLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        List<Project> resultList = entityManager.createQuery("SELECT p FROM Project p" +
                        " ORDER BY p.startDate DESC",
                Project.class)
                .setMaxResults(10)
                .getResultList();

       resultList.stream()
               .sorted((f, s) -> f.getName().compareTo(s.getName()))
               .forEach(p -> {
                   String printProject = "Project name: " + p.getName();
                   printProject += "\n\t" + "Project Description: " + p.getDescription();
                   printProject += "\n\t" + "Project Start Date: " + p.getStartDate();
                   printProject += "\n\t" + "Project End Date: " + p.getEndDate();

                   System.out.println(printProject);
               });


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
