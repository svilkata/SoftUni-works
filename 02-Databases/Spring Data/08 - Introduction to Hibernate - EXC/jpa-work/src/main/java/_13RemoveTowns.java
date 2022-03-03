import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class _13RemoveTowns {
    public static void main(String[] args) throws IOException {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
//        EntityManager entityManager = emf.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.getTransaction().commit();
//        entityManager.close();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        int counter = 0;
        try {
            //Градът за изтриване
            Town town = (Town) em.createQuery("FROM Town WHERE name =:parameter")
                    .setParameter("parameter", input)
                    .getSingleResult();

            //адресите в този град
            List<Address> addresses = em.createQuery("FROM Address WHERE town.id=:parameter1")
                    .setParameter("parameter1", town.getId())
                    .getResultList();

            em.getTransaction().begin();

            //за всеки адрес виж дали има служители на него, и ако адресът на служителя се съдържа в адресите на града, сетни го на null
            for (Address a : addresses) {
                for (Employee e : a.getEmployees()) {
                    if (addresses.contains(e.getAddress())) {
                        e.setAddress(null);
                        em.persist(e);
                    }
                }
                em.remove(a); //премахни дадения адрес
                counter++;
            }

            em.remove(town); //премахни дадения град
            em.getTransaction().commit();
            System.out.printf("%d addresses in %s were deleted", counter, input);

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
            factory.close();
        }
    }
}
