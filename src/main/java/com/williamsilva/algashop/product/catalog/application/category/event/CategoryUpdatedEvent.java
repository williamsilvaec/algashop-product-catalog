package com.williamsilva.algashop.product.catalog.application.category.event;

import java.util.UUID;

public class CategoryUpdatedEvent {

    private UUID categoryId;
    private String name;
    private Boolean enabled;

    public CategoryUpdatedEvent(UUID categoryId, String name, Boolean enabled) {
        this.categoryId = categoryId;
        this.name = name;
        this.enabled = enabled;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
