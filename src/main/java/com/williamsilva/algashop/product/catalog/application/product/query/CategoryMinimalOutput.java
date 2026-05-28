package com.williamsilva.algashop.product.catalog.application.product.query;

import com.williamsilva.algashop.product.catalog.infrastructure.utility.Slugfier;

import java.util.UUID;

public class CategoryMinimalOutput {

    private UUID id;
    private String name;
    private Boolean enabled;

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getSlug() {
        return Slugfier.slugify(this.getName());
    }

    public static class CategoryMinimalOutputBuilder {
        private UUID id;
        private String name;
        private Boolean enabled;

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

        public CategoryMinimalOutputBuilder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public CategoryMinimalOutput build() {
            CategoryMinimalOutput categoryMinimalOutput = new CategoryMinimalOutput();
            categoryMinimalOutput.setId(id);
            categoryMinimalOutput.setName(name);
            categoryMinimalOutput.setEnabled(enabled);
            return categoryMinimalOutput;
        }
    }
}
