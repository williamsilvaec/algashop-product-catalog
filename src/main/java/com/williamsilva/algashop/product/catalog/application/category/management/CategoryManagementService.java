package com.williamsilva.algashop.product.catalog.application.category.management;

import com.williamsilva.algashop.product.catalog.domain.model.category.Category;
import com.williamsilva.algashop.product.catalog.domain.model.category.CategoryNotFoundException;
import com.williamsilva.algashop.product.catalog.domain.model.category.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryManagementService {

    private final CategoryRepository categoryRepository;

    public CategoryManagementService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public UUID create(@Valid CategoryInput input) {
        Category category = new Category(input.name(), input.enabled());
        categoryRepository.save(category);
        return category.getId();
    }

    public void update(UUID categoryId, CategoryInput input) {
        Category category = findCategoryById(categoryId);

        category.setName(input.name());
        category.setEnabled(input.enabled());
        categoryRepository.save(category);
    }

    public void disable(UUID categoryId) {
        Category category = findCategoryById(categoryId);
        category.setEnabled(false);
        categoryRepository.save(category);
    }

    private Category findCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }
}
