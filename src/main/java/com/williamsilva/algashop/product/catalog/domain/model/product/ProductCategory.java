package com.williamsilva.algashop.product.catalog.domain.model.product;

import com.williamsilva.algashop.product.catalog.domain.model.category.Category;

import java.util.UUID;

public class ProductCategory {

    private UUID id;
    private String name;
    private Boolean enabled;

    protected ProductCategory() {
    }

    public ProductCategory(UUID id, String name, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
    }

    public static ProductCategory of(Category category) {
        return new ProductCategory(category.getId(), category.getName(), category.getEnabled());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
