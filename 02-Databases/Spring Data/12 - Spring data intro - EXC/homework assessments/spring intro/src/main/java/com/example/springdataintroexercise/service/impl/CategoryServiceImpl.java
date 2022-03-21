package com.example.springdataintroexercise.service.impl;

import com.example.springdataintroexercise.entity.Category;
import com.example.springdataintroexercise.repository.CategoryRepository;
import com.example.springdataintroexercise.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final String CATEGORIES_FILE_PATH = "src/main/resources/files/categories.txt";

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() > 0) {
            return;
        }
        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(c -> !c.isEmpty())
                .forEach(row -> {
                    categoryRepository.save(new Category(row));
                });
    }

    @Override
    public Set<Category> getRandomCategories() {
        int categoriesCnt = ThreadLocalRandom.current().nextInt(1, 4);
        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < categoriesCnt; i++) {
            long categoryId = ThreadLocalRandom.current()
                    .nextLong(1,
                            categoryRepository.count() + 1);
            Category category = categoryRepository.findById(categoryId).orElse(null);
            categories.add(category);
        }
        return categories;

    }
}
