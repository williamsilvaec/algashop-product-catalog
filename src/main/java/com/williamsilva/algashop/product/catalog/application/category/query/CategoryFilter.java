package com.williamsilva.algashop.product.catalog.application.category.query;

import java.util.Objects;

import org.springframework.data.domain.Sort;

import com.williamsilva.algashop.product.catalog.application.utility.SortablePageFilter;

public class CategoryFilter extends SortablePageFilter<CategoryFilter.SortType> {
    private String name;
    private Boolean enabled;

    public CategoryFilter() {
    }

    public CategoryFilter(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    @Override
    public CategoryFilter.SortType getSortByPropertyOrDefault() {
        return getSortByProperty() == null ? CategoryFilter.SortType.NAME: getSortByProperty();
    }

    @Override
    public Sort.Direction getSortDirectionOrDefault() {
        return getSortDirection() == null ? Sort.Direction.ASC : getSortDirection();
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

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof CategoryFilter)) {
            return false;
        }
        CategoryFilter categoryFilter = (CategoryFilter) object;
        if (!categoryFilter.canEqual(this)) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        return Objects.equals(getName(), categoryFilter.getName())
                && Objects.equals(getEnabled(), categoryFilter.getEnabled());
    }

    protected boolean canEqual(Object other) {
        return other instanceof CategoryFilter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getEnabled());
    }

    @Override
    public String toString() {
        return "CategoryFilter(name=" + getName()
                + ", enabled=" + getEnabled()
                + ")";
    }

    public enum SortType {
        NAME("name");

        private final String propertyName;

        SortType(String propertyName) {
            this.propertyName = propertyName;
        }

        public String getPropertyName() {
            return propertyName;
        }
    }
}
