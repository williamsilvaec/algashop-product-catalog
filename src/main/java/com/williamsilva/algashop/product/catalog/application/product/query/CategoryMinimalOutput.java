package com.williamsilva.algashop.product.catalog.application.product.query;

import java.util.UUID;

public class CategoryMinimalOutput {

    private UUID id;
    private String name;

    public static CategoryMinimalOutputBuilder builder() {
        return new CategoryMinimalOutputBuilder();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class CategoryMinimalOutputBuilder {
        private UUID id;
        private String name;

        CategoryMinimalOutputBuilder() {
        }

        public CategoryMinimalOutputBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public CategoryMinimalOutputBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CategoryMinimalOutput build() {
            CategoryMinimalOutput categoryMinimalOutput = new CategoryMinimalOutput();
            categoryMinimalOutput.setId(id);
            categoryMinimalOutput.setName(name);
            return categoryMinimalOutput;
        }
    }
}
