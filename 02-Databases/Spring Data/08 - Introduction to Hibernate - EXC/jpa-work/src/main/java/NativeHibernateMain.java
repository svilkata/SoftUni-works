import entities.Town;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class NativeHibernateMain {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();    //в скобите можем да сложим от кой файл искаме да го заредим

        SessionFactory sessionFactory = cfg.buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Town town = session.get(Town.class, 1);

        System.out.println(town);

        session.getTransaction().commit();
        session.close();
    }
}
