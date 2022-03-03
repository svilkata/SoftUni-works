import entities.Student;
import entities.Teacher;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("school");

            EntityManager em = emf.createEntityManager();


        em.getTransaction().begin();
//        Student student = new Student("Teo");
//        Student second = new Student("Gosho");
        Teacher teacher = new Teacher("Ivana", LocalDate.now());
        teacher.setName("Pavlina");

        em.persist(teacher);
//        em.persist(second);

//        Student firstFound = em.find(Student.class, 1);
//        em.remove(firstFound);

        em.getTransaction().commit();
        em.close();
    }
}






