package advquerying.services;

import advquerying.entities.Ingredient;
import advquerying.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> _04_selectNameStartsWith(String m) {
        return this.ingredientRepository.findByNameStartingWith(m);
    }

    @Override
    public List<Ingredient> _05_selectByNameOrderedByPrice(List<String> ingrList) {
        return this.ingredientRepository.findByNameInOrderByPriceAsc(ingrList);
    }

    @Override
    public int _09_deleteByName(String name) {
        //select all
        //delete
        //insert into archive
        return this.ingredientRepository.deleteByName(name);
    }

    @Override
    @Transactional
    public void _10_increasePriceByPercentage(double percent) {
        BigDecimal actualPercent = BigDecimal.valueOf(1 + percent); //преобразуване на данни в сервиса
        this.ingredientRepository.increasePriceByPercent(actualPercent);
    }

    @Override
    @Transactional
    public void _11_increasePriceByPercentageByAGivenList(double percentageIncr, Set<String> ingredients) {
        BigDecimal actualPercent = BigDecimal.valueOf(1 + percentageIncr); //преобразуване на данни в сервиса

        this.ingredientRepository.increasePriceByPercentByAGivenList(actualPercent, ingredients);
    }
}
