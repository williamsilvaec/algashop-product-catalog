package com.williamsilva.algashop.product.catalog.application.product.management;

import com.williamsilva.algashop.product.catalog.domain.model.product.Product;
import com.williamsilva.algashop.product.catalog.domain.model.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductManagementApplicationService {

    private final ProductRepository productRepository;

    public ProductManagementApplicationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        return Product.builder()
                .name(input.name())
                .brand(input.brand())
                .description(input.description())
                .regularPrice(input.regularPrice())
                .salePrice(input.salePrice())
                .enabled(input.enabled())
                .build();
    }
}
