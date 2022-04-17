package com.example.json_ex.productsshop;

import com.example.json_ex.productsshop.entities.categories.CategoryStats;
import com.example.json_ex.productsshop.entities.products.ProductWithoutBuyerDTO;
import com.example.json_ex.productsshop.entities.users.User;
import com.example.json_ex.productsshop.entities.users.UserWithSoldProductsDTO;
import com.example.json_ex.productsshop.services.ProductService;
import com.example.json_ex.productsshop.services.SeedService;
import com.example.json_ex.productsshop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductShopRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductService productService;
    private UserService userService;

    private final Gson gson;


    @Autowired
    public ProductShopRunner(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();
//        _01_Query_ProductsInRange();
//        _02_Query_SuccessfullySoldProducts();
//        _03_Query_CategoriesByProductsCount();
        _04_Query_UsersAndProducts();
    }

    private void _01_Query_ProductsInRange() {
        List<ProductWithoutBuyerDTO> productsForSell = this.productService.getProductsInPriceRangeForSell(500.0f, 1000.0f);
        String jsonContent = this.gson.toJson(productsForSell);
        System.out.println(jsonContent);
    }

    private void _02_Query_SuccessfullySoldProducts() {
        List<UserWithSoldProductsDTO> usersWithSoldProducts = this.userService.getUsersWithSoldProducts();
        String jsonContent = this.gson.toJson(usersWithSoldProducts);
        System.out.println(jsonContent);
    }

    private void _03_Query_CategoriesByProductsCount() {
        List<CategoryStats> categoryStatistics = this.productService.getCategoryStatistics();
        String jsonContent = this.gson.toJson(categoryStatistics);
        System.out.println(jsonContent);
    }

    private void _04_Query_UsersAndProducts() {
        List<User> output = this.userService.getUsersWithSoldProductsOrderedByCount();
        int a = 5;
        String jsonContent = this.gson.toJson(output);
        System.out.println(jsonContent);
    }
}
