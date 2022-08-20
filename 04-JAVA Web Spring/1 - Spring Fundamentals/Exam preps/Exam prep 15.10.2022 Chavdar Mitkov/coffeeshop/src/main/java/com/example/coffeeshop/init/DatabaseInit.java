package com.example.coffeeshop.init;

import com.example.coffeeshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {
    private final CategoryService categoryService;

    @Autowired
    public DatabaseInit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    //този run метод ще се стартира при стратиране на нашето приложение
    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategories();
    }
}
