package com.williamsilva.algashop.product.catalog.infrastructure.persistence.category;

import com.williamsilva.algashop.product.catalog.application.category.query.CategoryDetailOutput;
import com.williamsilva.algashop.product.catalog.application.category.query.CategoryQueryService;
import com.williamsilva.algashop.product.catalog.application.product.query.PageModel;
import com.williamsilva.algashop.product.catalog.domain.model.category.Category;
import com.williamsilva.algashop.product.catalog.domain.model.category.CategoryNotFoundException;
import com.williamsilva.algashop.product.catalog.domain.model.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository repository;

    public CategoryQueryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public PageModel<CategoryDetailOutput> filter(Integer size, Integer number) {
        return null;
    }

    @Override
    public CategoryDetailOutput findById(UUID categoryId) {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        return convert(category);
    }

    private CategoryDetailOutput convert(Category category) {
        return new CategoryDetailOutput(category.getId(), category.getName(), category.getEnabled());
    }
}
