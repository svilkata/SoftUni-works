package com.soft_uni.jsonprocessing;

import com.google.gson.Gson;
import com.soft_uni.jsonprocessing.model.dto.CategoryDto.CategoryViewDto;
import com.soft_uni.jsonprocessing.model.dto.ProductDto.ProductNameAndPriceDto;
import com.soft_uni.jsonprocessing.model.dto.ProductDto.ProductNamePriceAndSellerDto;
import com.soft_uni.jsonprocessing.model.dto.ProductDto.SoldProductsDto;
import com.soft_uni.jsonprocessing.model.dto.UserDto.UserCountDto;
import com.soft_uni.jsonprocessing.model.dto.UserDto.UserNameAgeAndSoldProductsDto;
import com.soft_uni.jsonprocessing.model.dto.UserDto.UserSellerDto;
import com.soft_uni.jsonprocessing.service.interfaces.CategoryService;
import com.soft_uni.jsonprocessing.service.interfaces.ProductService;
import com.soft_uni.jsonprocessing.service.interfaces.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;
    private final Gson gson;
    private static final String JSON_OUT_PATH = "src/main/resources/files/out/";
    private static final String PRODUCTS_IN_RANGE_FILE_NAME = "products-in-range.json";
    private static final String SOLD_PRODUCTS_FILE_NAME = "sold-products.json";
    private static final String CATEGORIES_VIEW_FILE_NAME = "categories-view.json";
    private static final String USERS_VIEW_FILE_NAME = "users-view.json";

    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService, ProductService productService, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.gson = gson;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.println("Enter query number (1-4)");
        String input = bufferedReader.readLine();
        switch (input) {
            case "1" -> productsInRange();
            case "2" -> soldProducts();
            case "3" -> categoriesCount();
            case "4" -> userWithSoldProducts();
        }

    }

    private void userWithSoldProducts() throws IOException {
        List<UserNameAgeAndSoldProductsDto> users = userService.getUsersWithMoreThanOneProductSold();
        for (UserNameAgeAndSoldProductsDto user : users) {
            List<ProductNameAndPriceDto> productNameAndPriceDto =
                    productService.getProductsBySellerIdAndBuyerNotNull(user.getId());
            user.setSoldProducts(new SoldProductsDto(productNameAndPriceDto.size(), productNameAndPriceDto));
        }
        UserCountDto userCountDto = new UserCountDto(users.size(), users);
        String infoOut = gson.toJson(userCountDto);
        Files.writeString(Path.of(JSON_OUT_PATH + USERS_VIEW_FILE_NAME), infoOut);

    }

    private void categoriesCount() throws IOException {
        List<CategoryViewDto> categoriesByCount = categoryService.findCategoriesByCount();
        String infoOut = gson.toJson(categoriesByCount);
        Files.writeString(Path.of(JSON_OUT_PATH + CATEGORIES_VIEW_FILE_NAME), infoOut);
    }

    private void soldProducts() throws IOException {
        List<UserSellerDto> userSellerDtos = userService.getAllUsersWithAtLeastOneProductSold();
        String infoOut = gson.toJson(userSellerDtos);
        Files.writeString(Path.of(JSON_OUT_PATH + SOLD_PRODUCTS_FILE_NAME), infoOut);
    }

    private void productsInRange() throws IOException {
        System.out.println("Enter bottom bound:");
        BigDecimal bottomBound = new BigDecimal(bufferedReader.readLine());
        System.out.println("Enter upper bound:");
        BigDecimal upperBound = new BigDecimal(bufferedReader.readLine());
        List<ProductNamePriceAndSellerDto> productDtos = productService
                .findALLProductsWithPriceBetween(bottomBound, upperBound);
        System.out.println();
        String infoOut = gson.toJson(productDtos);
        Files.writeString(Path.of(JSON_OUT_PATH + PRODUCTS_IN_RANGE_FILE_NAME), infoOut);

    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        userService.seedUsers();
        productService.seedProducts();
    }
}
