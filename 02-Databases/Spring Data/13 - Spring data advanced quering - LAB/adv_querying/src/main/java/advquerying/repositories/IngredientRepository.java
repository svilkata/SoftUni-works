package advquerying.repositories;

import advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByNameStartingWith(String m);

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> ingrList);

    @Transactional   //тука го слагаме
    @Modifying
    int deleteByName(String name);

    @Modifying
    @Query("UPDATE Ingredient i SET i.price = i.price * :multiplier")  //тук си го умножава BigDecimal без проблем
    void increasePriceByPercent(@Param(value = "multiplier") BigDecimal percent);

    @Modifying
    @Query("UPDATE Ingredient i SET i.price = i.price * :multiplier WHERE i.name IN :listIngr")
    void increasePriceByPercentByAGivenList(@Param(value = "multiplier") BigDecimal percent,
                                            @Param(value = "listIngr") Set<String> ingredients);
}
