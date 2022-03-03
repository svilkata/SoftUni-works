import demos.hibernate.model.Student;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateDemoWithXMLNoAnnotationsMain {
    public static void main(String[] args) {
        Configuration config = new Configuration();   //Service registry
//        config.configure("\\src\\main\\resources\\META-INF\\hibernate.config.xml");    //в скобите можем да сложим от кой файл искаме да го заредим
//        config.configure("src/java/hibernate.cfg.xml");    //в скобите можем да сложим от кой файл искаме да го заредим
        config.configure();    //в скобите можем да сложим от кой файл искаме да го заредим

        //try with resources / try-with-resources
        try (SessionFactory sessionFactory = config.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            Student student = new Student("Ivan Petrov");
            try {
                session.save(student);
                session.persist(student);
//                session.detach(student);
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                throw e;
            }

            session.beginTransaction();
            session.setHibernateFlushMode(FlushMode.MANUAL);
            session.getTransaction().commit();     //transaction commit
            session.close();
        }
    }
}
