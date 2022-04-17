package com.soft_uni.jsonprocessing.service.impl;

import com.google.gson.Gson;
import com.soft_uni.jsonprocessing.model.dto.CategoryDto.CategorySeedDto;
import com.soft_uni.jsonprocessing.model.dto.CategoryDto.CategoryViewDto;
import com.soft_uni.jsonprocessing.model.entity.Category;
import com.soft_uni.jsonprocessing.repository.CategoryRepository;
import com.soft_uni.jsonprocessing.service.interfaces.CategoryService;
import com.soft_uni.jsonprocessing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.soft_uni.jsonprocessing.constants.Constants.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String JSON_FILE_PATH = "categories.json";
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(Gson gson, ValidationUtil validationUtil, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() > 0) {
            return;
        }

        String categoriesContent = Files
                .readString(Path.of(RESOURCE_FILE_PATH + JSON_FILE_PATH));

        CategorySeedDto[] categories = gson.
                fromJson(categoriesContent, CategorySeedDto[].class);
        Arrays.stream(categories)
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                .forEach(categoryRepository::save);

    }

    @Override
    public Set<Category> findRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int categoryCount = ThreadLocalRandom.current().nextInt(1, 4);
        long totalCategories = categoryRepository.count();
        for (int i = 0; i < categoryCount; i++) {
            Long randomId = ThreadLocalRandom.current().nextLong(1, totalCategories + 1);
            categories.
                    add(categoryRepository.
                            findById(randomId)
                            .orElse(null));
        }
        return categories;
    }

    @Override
    public List<CategoryViewDto> findCategoriesByCount() {
        return categoryRepository.findAllByProductCount().stream()
                .map(objects -> new CategoryViewDto((String) objects[0], (BigInteger) objects[1],
                            (BigDecimal) objects[2], (BigDecimal) objects[3]))
                .collect(Collectors.toList());
    }
}


