import entities.shampoo.BasicIngredient;
import entities.shampoo.BasicLabel;
import entities.shampoo.BasicShampoo;
import entities.shampoo.ProductionBatch;
import net.bytebuddy.matcher.InheritedAnnotationMatcher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        ProductionBatch batch = new ProductionBatch(LocalDate.now());
        BasicLabel label = new BasicLabel("blue");
        BasicShampoo shampoo = new BasicShampoo("shower", label, batch);

        BasicIngredient ingredient = new BasicIngredient(100, "B12");
        BasicIngredient ingredient2 = new BasicIngredient(2, "Violet");

        shampoo.addIngredient(ingredient);
        shampoo.addIngredient(ingredient2);
        entityManager.persist(ingredient);
        entityManager.persist(ingredient2);

        entityManager.persist(batch);
        entityManager.persist(label);
        entityManager.persist(shampoo);

        ProductionBatch productionBatch = entityManager.find(ProductionBatch.class, 1);
        Set<BasicShampoo> shampoos = productionBatch.getShampoos();

        List<String> names = Arrays.asList("Pesho", "Gosho", "Kiro");
        shampoo.setSomeNames(names);

        System.out.println(shampoos);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
