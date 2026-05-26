package com.williamsilva.algashop.product.catalog.application.category.query;

import java.util.UUID;

public record CategoryDetailOutput(UUID id, String name, Boolean enabled) {

    public static CategoryDetailOutputBuilder builder() {
        return new CategoryDetailOutputBuilder();
    }

    public static class CategoryDetailOutputBuilder {
        private UUID id;
        private String name;
        private Boolean enabled;

        CategoryDetailOutputBuilder() {
        }

        public CategoryDetailOutputBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public CategoryDetailOutputBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CategoryDetailOutputBuilder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public CategoryDetailOutput build() {
            return new CategoryDetailOutput(id, name, enabled);
        }
    }
}
