package com.soft_uni.jsonprocessing.service.interfaces;

import com.soft_uni.jsonprocessing.model.dto.CategoryDto.CategoryViewDto;
import com.soft_uni.jsonprocessing.model.entity.Category;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> findRandomCategories();
    List<CategoryViewDto> findCategoriesByCount();

}
