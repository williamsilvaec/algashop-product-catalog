package com.williamsilva.algashop.product.catalog.application.product.query;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ProductSummaryOutput {

    private UUID id;
    private OffsetDateTime addedAt;
    private String name;
    private String brand;
    private BigDecimal regularPrice;
    private BigDecimal salePrice;
    private Boolean inStock;
    private Boolean enabled;
    private CategoryMinimalOutput category;

    private String shortDescription;

    private String slug;
    private Boolean hasDiscount;

    private Integer quantityInStock;
    private Integer discountPercentageRounded;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OffsetDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(OffsetDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public CategoryMinimalOutput getCategory() {
        return category;
    }

    public void setCategory(CategoryMinimalOutput category) {
        this.category = category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Integer getDiscountPercentageRounded() {
        return discountPercentageRounded;
    }

    public void setDiscountPercentageRounded(Integer discountPercentageRounded) {
        this.discountPercentageRounded = discountPercentageRounded;
    }
}
