package com.example.shoping.service;

import com.example.shoping.models.entities.CategoryEntity;
import com.example.shoping.models.entities.enums.CategoryName;
import com.example.shoping.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void initCategories() {
        if (this.categoryRepository.count() == 0) {
            List<CategoryEntity> categories = Arrays.stream(CategoryName.values())
                    .map(type -> {
                        CategoryEntity categoryEntity = new CategoryEntity(type);
                        switch (type.name()) {
                            case "FOOD":
                                categoryEntity.setDescription("Delicious food");
                                break;
                            case "DRINK":
                                categoryEntity.setDescription("Good drink");
                                break;
                            case "HOUSEHOLD":
                                categoryEntity.setDescription("Household stuff");
                                break;
                            case "OTHER":
                                categoryEntity.setDescription("Ot5her stuff");
                                break;
                        }

                        return categoryEntity;
                    }).collect(Collectors.toList());

            this.categoryRepository.saveAll(categories);
        }

    }
}
