package com.williamsilva.algashop.product.catalog.application.product.query;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ProductDetailOutput {

    private UUID id;
    private OffsetDateTime addedAt;
    private String name;
    private String brand;
    private BigDecimal regularPrice;
    private BigDecimal salePrice;
    private Boolean inStock;
    private Boolean enabled;
    private CategoryMinimalOutput category;
    private String description;

    private String slug;
    private Boolean hasDiscount;

    private Integer quantityInStock;
    private Integer discountPercentageRounded;

    public static ProductDetailOutputBuilder builder() {
        return new ProductDetailOutputBuilder();
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public static class ProductDetailOutputBuilder {
        private UUID id;
        private OffsetDateTime addedAt;
        private String name;
        private String brand;
        private BigDecimal regularPrice;
        private BigDecimal salePrice;
        private Boolean inStock;
        private Boolean enabled;
        private CategoryMinimalOutput category;
        private String description;
        private String slug;
        private Boolean hasDiscount;
        private Integer quantityInStock;
        private Integer discountPercentageRounded;

        ProductDetailOutputBuilder() {
        }

        public ProductDetailOutputBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public ProductDetailOutputBuilder addedAt(OffsetDateTime addedAt) {
            this.addedAt = addedAt;
            return this;
        }

        public ProductDetailOutputBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductDetailOutputBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public ProductDetailOutputBuilder regularPrice(BigDecimal regularPrice) {
            this.regularPrice = regularPrice;
            return this;
        }

        public ProductDetailOutputBuilder salePrice(BigDecimal salePrice) {
            this.salePrice = salePrice;
            return this;
        }

        public ProductDetailOutputBuilder inStock(Boolean inStock) {
            this.inStock = inStock;
            return this;
        }

        public ProductDetailOutputBuilder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public ProductDetailOutputBuilder category(CategoryMinimalOutput category) {
            this.category = category;
            return this;
        }

        public ProductDetailOutputBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductDetailOutputBuilder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public ProductDetailOutputBuilder hasDiscount(Boolean hasDiscount) {
            this.hasDiscount = hasDiscount;
            return this;
        }

        public ProductDetailOutputBuilder quantityInStock(Integer quantityInStock) {
            this.quantityInStock = quantityInStock;
            return this;
        }

        public ProductDetailOutputBuilder discountPercentageRounded(Integer discountPercentageRounded) {
            this.discountPercentageRounded = discountPercentageRounded;
            return this;
        }

        public ProductDetailOutput build() {
            ProductDetailOutput productDetailOutput = new ProductDetailOutput();
            productDetailOutput.setId(id);
            productDetailOutput.setAddedAt(addedAt);
            productDetailOutput.setName(name);
            productDetailOutput.setBrand(brand);
            productDetailOutput.setRegularPrice(regularPrice);
            productDetailOutput.setSalePrice(salePrice);
            productDetailOutput.setInStock(inStock);
            productDetailOutput.setEnabled(enabled);
            productDetailOutput.setCategory(category);
            productDetailOutput.setDescription(description);
            productDetailOutput.setSlug(slug);
            productDetailOutput.setHasDiscount(hasDiscount);
            productDetailOutput.setQuantityInStock(quantityInStock);
            productDetailOutput.setDiscountPercentageRounded(discountPercentageRounded);
            return productDetailOutput;
        }
    }
}
