package demo.services;

import demo.entities.Category;
import demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        long size = this.categoryRepository.count();
        Random random = new Random();

        int categoriesCount = random.nextInt((int) size) + 1;

        Set<Integer> categoriesIDs = new HashSet<>();
        for (int i = 0; i < categoriesCount; i++) {
            int nextId = random.nextInt((int) size) + 1;

            categoriesIDs.add(nextId);
        }

        List<Category> allById = this.categoryRepository.findAllById(categoriesIDs);

        return new HashSet<>(allById);
    }
}
