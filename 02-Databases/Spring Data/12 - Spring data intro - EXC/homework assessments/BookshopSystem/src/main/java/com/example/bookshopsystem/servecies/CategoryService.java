package com.example.bookshopsystem.servecies;

import com.example.bookshopsystem.entities.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> getRandomCategories();
}
