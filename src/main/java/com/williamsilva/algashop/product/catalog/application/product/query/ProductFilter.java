package com.williamsilva.algashop.product.catalog.application.product.query;

import com.williamsilva.algashop.product.catalog.application.utility.SortablePageFilter;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ProductFilter extends SortablePageFilter<ProductFilter.SortType> {

    private String term;
    private Boolean hasDiscount;
    private Boolean enabled;
    private Boolean inStock;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private UUID[] categoriesId;
    private OffsetDateTime addedAtFrom;
    private OffsetDateTime addedAtTo;

    public ProductFilter() {
        super();
    }

    public ProductFilter(int size, int page) {
        super(size, page);
    }

    @Override
    public SortType getSortByPropertyOrDefault() {
        return SortType.ADDED_AT;
    }

    @Override
    public Sort.Direction getSortDirectionOrDefault() {
        return Sort.Direction.ASC;
    }

    public enum SortType {
        ADDED_AT("addedAt"),
        SALE_PRICE("salePrice");

        private final String propertyName;

        SortType(String propertyName) {
            this.propertyName = propertyName;
        }

        public String getPropertyName() {
            return propertyName;
        }
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public BigDecimal getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }

    public UUID[] getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(UUID[] categoriesId) {
        this.categoriesId = categoriesId;
    }

    public OffsetDateTime getAddedAtFrom() {
        return addedAtFrom;
    }

    public void setAddedAtFrom(OffsetDateTime addedAtFrom) {
        this.addedAtFrom = addedAtFrom;
    }

    public OffsetDateTime getAddedAtTo() {
        return addedAtTo;
    }

    public void setAddedAtTo(OffsetDateTime addedAtTo) {
        this.addedAtTo = addedAtTo;
    }
}
