package com.williamsilva.algashop.product.catalog.application.product.management;

import com.williamsilva.algashop.product.catalog.domain.model.category.Category;
import com.williamsilva.algashop.product.catalog.domain.model.category.CategoryNotFoundException;
import com.williamsilva.algashop.product.catalog.domain.model.category.CategoryRepository;
import com.williamsilva.algashop.product.catalog.domain.model.product.Product;
import com.williamsilva.algashop.product.catalog.domain.model.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductManagementApplicationService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductManagementApplicationService(ProductRepository productRepository,
                                               CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public UUID create(ProductInput input) {
        Product product = mapToProduct(input);
        productRepository.save(product);
        return product.getId();
    }

    public void update(UUID product, ProductInput input) {

    }

    public void disable(UUID product) {

    }

    private Product mapToProduct(ProductInput input) {
        Category category = findCategory(input.categoryId());
        return Product.builder()
                .name(input.name())
                .brand(input.brand())
                .description(input.description())
                .regularPrice(input.regularPrice())
                .salePrice(input.salePrice())
                .enabled(input.enabled())
                .build();
    }

    private Category findCategory(UUID category) {
        return categoryRepository.findById(category)
                .orElseThrow(() -> new CategoryNotFoundException(category));
    }
}
