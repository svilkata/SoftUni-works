import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class _02_ChangeCasing_JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();


        //Подход с CriteriaBuilder
        /*
        CriteriaBuilder builder = entityManager.getCriteriaBuilder(); //by CriteriaBuilder it is type-safe
        CriteriaQuery criteria = builder.createQuery();
        Root<Town> r = criteria.from(Town.class);
        criteria.select(r).where(builder.like(r.get("name"), "P%"));
        */

        //Подход с директна Update JPQL заявка в базата данни
//        entityManager.createQuery("UPDATE Town t SET t.name=lower(t.name) WHERE length(t.name) >=5");

        //Подход взимаме нещата от базата, правим нещо с данните, и записваме/update-ваме само което ни трябва
        Query from_town = entityManager.createQuery("SELECT t FROM Town t", Town.class);
        List<Town> resultList = from_town.getResultList();

        for (Town town : resultList) {
            String name = town.getName();
            if (name.length() <= 5) {
                String toUpper = name.toUpperCase();
                town.setName(toUpper);

                entityManager.persist(town);
            }
        }


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
