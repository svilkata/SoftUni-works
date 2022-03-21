package advquerying.repositories;

import advquerying.entities.Ingredient;
import advquerying.entities.Shampoo;
import advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findByBrand(String brand);
    List<Shampoo> findBySize(Size size);

    List<Shampoo> findByBrandAndSize(String brand, Size size);

    List<Shampoo> findBySizeOrderById(Size size);

    @Query("SELECT s FROM Shampoo s" +
            " INNER JOIN s.ingredients AS i"+
            " WHERE i.name IN :ingredientNames")
    List<Shampoo> findByIngredientsNames(
            @Param(value = "ingredientNames") Set<String> ingredientNames);


    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, long labelId);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    int countByPriceLessThan(BigDecimal price);

    @Query("SELECT s FROM Shampoo s" +
            " WHERE s.ingredients.size < :count")
    List<Shampoo> findByIngredientCountLessThan(@Param(value = "count")int count);
}

