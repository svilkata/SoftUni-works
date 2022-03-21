package com.example._12_spring_data_intro_exercise.services;

import com.example._12_spring_data_intro_exercise.entities.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
