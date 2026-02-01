package com.williamsilva.algashop.product.catalog.application.product.management;

import com.williamsilva.algashop.product.catalog.domain.model.category.Category;
import com.williamsilva.algashop.product.catalog.domain.model.category.CategoryNotFoundException;
import com.williamsilva.algashop.product.catalog.domain.model.category.CategoryRepository;
import com.williamsilva.algashop.product.catalog.domain.model.product.Product;
import com.williamsilva.algashop.product.catalog.domain.model.product.ProductNotFoundException;
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

    public void update(UUID productId, ProductInput input) {
        Product product = findProduct(productId);
        Category category = findCategory(input.categoryId());

        updateProduct(product, input);
        product.setCategory(category);

        productRepository.save(product);
    }

    public void disable(UUID productId) {
        Product product = findProduct(productId);
        product.disable();

        productRepository.save(product);
    }

    public void enable(UUID productId) {
        Product product = findProduct(productId);
        product.enable();

        productRepository.save(product);
    }

    private void updateProduct(Product product, ProductInput input) {
        product.setName(input.name());
        product.setBrand(input.brand());
        product.setDescription(input.description());
        product.setRegularPrice(input.regularPrice());
        product.setSalePrice(input.salePrice());
        product.setEnabled(input.enabled());
    }

    private Product findProduct(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
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
                .category(category)
                .build();
    }

    private Category findCategory(UUID category) {
        return categoryRepository.findById(category)
                .orElseThrow(() -> new CategoryNotFoundException(category));
    }
}
