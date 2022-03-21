package advquerying.services;

import advquerying.entities.Shampoo;
import advquerying.entities.Size;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {
    List<Shampoo> _01_selectBySize(Size size);

    List<Shampoo> _02_selectBySizeOrLabel(Size size, long labelId);

    List<Shampoo> _03_selectByPrice(BigDecimal price);

    int _06_countShampPriceLowerThan(BigDecimal price);

    List<Shampoo> _07_selectByIngredientsNames(Set<String> names);

    List<Shampoo> _08_selectByIngredientCountLessThan(int i);

    void _12PrintInPages5();
}
