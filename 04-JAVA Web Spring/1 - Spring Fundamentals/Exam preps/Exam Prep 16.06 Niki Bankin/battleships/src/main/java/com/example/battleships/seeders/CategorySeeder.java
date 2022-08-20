package com.example.battleships.seeders;

import com.example.battleships.models.entities.CategoryEntity;
import com.example.battleships.models.entities.enums.ShipType;
import com.example.battleships.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategorySeeder implements CommandLineRunner {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(ShipType.values())
                    .map(type -> {
                        CategoryEntity categoryEntity = new CategoryEntity(type);
                        switch (type.ordinal()){
                            case 0: categoryEntity.setDescription("BATTLE"); break;
                            case 1: categoryEntity.setDescription("CARGO"); break;
                            case 2: categoryEntity.setDescription("PATROL");break;
                        }

                        return categoryEntity;
                    })
                    .forEach(cat -> this.categoryRepository.save(cat));
        }
    }
}
