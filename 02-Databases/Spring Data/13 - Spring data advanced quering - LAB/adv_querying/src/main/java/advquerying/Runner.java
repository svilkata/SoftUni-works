package advquerying;

import advquerying.entities.Ingredient;
import advquerying.entities.Shampoo;
import advquerying.entities.Size;
import advquerying.services.IngredientService;
import advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Runner implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public Runner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
//        _01_SelectShampoosbySize();
//        _02_SelectShampoosbySizeOrLabel();
//        _03_SelectShampoosbyPrice();
//        _04_SelectIngredientsStartingByGivenLetter();
//        _05_SelectIngredientsByNameOrderedByPrice();
//        _06_SelectCountAllShampoosWithPriceLowerThan();
//        _07_SelectShampoosbyIngredients();
//        _08_SelectShampoosbyIngredientsCount();
//        _09_DeleteIngredientsbyName();
//        _10_UpdateIngredientsbyPrice();
//        _11_UpdateIngredientsbyPriceByAGivenList();
        _12_PrintInPagesOf5();
    }

    private void _12_PrintInPagesOf5() {
        shampooService._12PrintInPages5();
    }


    private void _01_SelectShampoosbySize() {
        Scanner sc = new Scanner(System.in);
        String sizeName = sc.nextLine();
        Size size = Size.valueOf(sizeName);

        this.shampooService._01_selectBySize(size)
                .forEach(System.out::println);
    }

    private void _02_SelectShampoosbySizeOrLabel() {
        Scanner sc = new Scanner(System.in);
        String sizeName = sc.nextLine();
        Size size = Size.valueOf(sizeName);
        long labelId = Long.parseLong(sc.nextLine());

        this.shampooService._02_selectBySizeOrLabel(size, labelId)
                .forEach(System.out::println);
    }

    private void _03_SelectShampoosbyPrice() {
        Scanner sc = new Scanner(System.in);
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));

        this.shampooService._03_selectByPrice(price)
                .forEach(System.out::println);
    }

    private void _04_SelectIngredientsStartingByGivenLetter() {
        this.ingredientService._04_selectNameStartsWith("M")
                .forEach(System.out::println);
    }

    private void _05_SelectIngredientsByNameOrderedByPrice() {
        List<String> ingrList = List.of("Lavender", "Herbs", "Apple");

        this.ingredientService._05_selectByNameOrderedByPrice(ingrList)
                .forEach(System.out::println);
    }

    private void _06_SelectCountAllShampoosWithPriceLowerThan() {
        System.out.println(this.shampooService._06_countShampPriceLowerThan(BigDecimal.valueOf(8.5)));
    }

    private void _07_SelectShampoosbyIngredients() {
        Scanner sc = new Scanner(System.in);
        String first = sc.nextLine();
        String second = sc.nextLine();

        Set<String> names = Set.of(first, second);
        Set<String> collect = this.shampooService._07_selectByIngredientsNames(names)
                .stream()
                .map(s -> s.getBrand())
                .collect(Collectors.toSet());

        collect.forEach(System.out::println);
    }

    private void _08_SelectShampoosbyIngredientsCount() {
        this.shampooService._08_selectByIngredientCountLessThan(2)
                .forEach(System.out::println);
    }

    private void _09_DeleteIngredientsbyName() {
        this.ingredientService._09_deleteByName("Nettle");
    }

    private void _10_UpdateIngredientsbyPrice() {
        this.ingredientService._10_increasePriceByPercentage(0.1);
    }

    private void _11_UpdateIngredientsbyPriceByAGivenList() {
        Set<String> ingredients = Set.of("Apple", "Macadamia Oil");
        this.ingredientService._11_increasePriceByPercentageByAGivenList(0.5, ingredients);
    }
}



