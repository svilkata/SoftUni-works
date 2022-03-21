package advquerying.services;

import advquerying.entities.Ingredient;

import java.util.List;
import java.util.Set;

public interface IngredientService {
    List<Ingredient> _04_selectNameStartsWith(String m);

    List<Ingredient> _05_selectByNameOrderedByPrice(List<String> ingrList);

    int _09_deleteByName(String nettle);

    void _10_increasePriceByPercentage(double v);


    void _11_increasePriceByPercentageByAGivenList(double v, Set<String> ingredients);
}
