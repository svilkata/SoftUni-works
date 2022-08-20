package com.example.shoping.seeders;


import com.example.shoping.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategorySeeder implements CommandLineRunner {
    private final CategoryServiceImpl categoryService;

    @Autowired
    public CategorySeeder(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initCategories();
    }
}
