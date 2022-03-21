package advquerying.services;

import advquerying.entities.Ingredient;
import advquerying.entities.Label;
import advquerying.entities.Shampoo;
import advquerying.entities.Size;
import advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShampooServiceImpl implements ShampooService {
    @Autowired
    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> _01_selectBySize(Size size) {
        return this.shampooRepository.findBySizeOrderById(size);
    }

    @Override
    public List<Shampoo> _02_selectBySizeOrLabel(Size size, long labelId) {
//        Label label = this.labelRepository.findById(labelId);

        return this.shampooRepository.findBySizeOrLabelIdOrderByPriceAsc(size, labelId);
    }

    @Override
    public List<Shampoo> _03_selectByPrice(BigDecimal price) {
        return this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public int _06_countShampPriceLowerThan(BigDecimal price) {

        return this.shampooRepository.countByPriceLessThan(price);
    }

    @Override
    public List<Shampoo> _07_selectByIngredientsNames(Set<String> names) {
        return this.shampooRepository.findByIngredientsNames(names);
    }

    @Override
    public List<Shampoo> _08_selectByIngredientCountLessThan(int count) {
        return this.shampooRepository.findByIngredientCountLessThan(count);
    }

    @Override
    public void _12PrintInPages5() {
        Page<Shampoo> page = shampooRepository.findAll(PageRequest.of(0, 5));

        System.out.printf("Page %d of %d:%n--------------%n",
                page.getNumber()+1, page.getTotalPages()
        );

        page.forEach(s -> System.out.printf("%s %s %s %.2f %s%n",
                s.getBrand(), s.getSize(), s.getLabel().getTitle(), s.getPrice(),
                s.getIngredients().stream().map(Ingredient::getName).collect(Collectors.toList())));

        while (page.hasNext()) {
            page = shampooRepository.findAll(page.nextPageable());
            System.out.printf("Page %d of %d:%n--------------%n",
                    page.getNumber()+1, page.getTotalPages()
            );

            page.forEach(s -> System.out.printf("%s %s %s %.2f %s%n",
                    s.getBrand(), s.getSize(), s.getLabel().getTitle(), s.getPrice(),
                    s.getIngredients().stream().map(Ingredient::getName).collect(Collectors.toList())));
        }
    }
}
