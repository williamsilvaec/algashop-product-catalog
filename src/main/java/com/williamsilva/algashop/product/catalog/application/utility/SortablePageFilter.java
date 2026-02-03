package com.williamsilva.algashop.product.catalog.application.utility;

import org.springframework.data.domain.Sort;

public abstract class SortablePageFilter<T> extends PageFilter {

    private T sortByProperty;
    private Sort.Direction sortDirection;

    public SortablePageFilter() {
    }

    public SortablePageFilter(int size, int page) {
        super(size, page);
    }

    public abstract T getSortByPropertyOrDefault();
    public abstract Sort.Direction getSortDirectionOrDefault();

    public T getSortByProperty() {
        return sortByProperty;
    }

    public void setSortByProperty(T sortByProperty) {
        this.sortByProperty = sortByProperty;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }
}
