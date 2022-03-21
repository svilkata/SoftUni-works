package com.example.bookshopsystem.servecies;

import com.example.bookshopsystem.entities.Category;
import com.example.bookshopsystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        long count = this.categoryRepository.count();
        Random random = new Random();

        int categoriesCount = random.nextInt((int) count) + 1;

        Set<Integer> categoriesIds = new HashSet<>();

        for (int i = 0; i <categoriesCount ; i++) {
            int nextId = random.nextInt((int) count) + 1;

            categoriesIds.add(nextId);
        }

         List<Category> allById =  this.categoryRepository.findAllById(categoriesIds);

        return new HashSet<>(allById);
    }
}
