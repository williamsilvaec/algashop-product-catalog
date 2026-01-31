package com.williamsilva.algashop.product.catalog.domain.model.product;

import com.williamsilva.algashop.product.catalog.domain.model.DomainException;
import com.williamsilva.algashop.product.catalog.domain.model.IdGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "products")
public class Product {

    @Id
    private UUID id;

    private String name;
    private String brand;
    private String description;
    private Integer quantityInStock;
    private Boolean enabled;
    private BigDecimal regularPrice;
    private BigDecimal salePrice;

    @Version
    private Long version;

    @CreatedDate
    private OffsetDateTime addedAt;

    @LastModifiedDate
    private OffsetDateTime updatedAt;

    @CreatedBy
    private UUID createdByUserId;

    @LastModifiedBy
    private UUID lastModifiedByUserId;

    protected Product() {}

    public Product(String name, String brand, String description,
                   Boolean enabled, BigDecimal regularPrice, BigDecimal salePrice) {
        this.setId(IdGenerator.generateTimeBasedUUID());
        this.setName(name);
        this.setBrand(brand);
        this.setDescription(description);
        this.setEnabled(enabled);
        this.setRegularPrice(regularPrice);
        this.setSalePrice(salePrice);
    }

    public void setName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public void setBrand(String brand) {
        if (!StringUtils.hasText(brand)) {
            throw new IllegalArgumentException();
        }
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        Objects.requireNonNull(regularPrice);
        if (regularPrice.signum() == -1) {
            throw new IllegalArgumentException();
        }

        if (this.salePrice == null) {
            this.salePrice = regularPrice;
        } else if (regularPrice.compareTo(this.salePrice) < 0) {
            throw new DomainException("Sale price cannot be greater than regular price");
        }

        this.regularPrice = regularPrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        Objects.requireNonNull(salePrice);
        if (salePrice.signum() == -1) {
            throw new IllegalArgumentException();
        }

        if (this.regularPrice == null) {
            this.regularPrice = salePrice;
        } else if (this.regularPrice.compareTo(salePrice) < 0) {
            throw new DomainException("Sale price cannot be greater than regular price");
        }

        this.salePrice = salePrice;
    }

    public void setEnabled(Boolean enabled) {
        Objects.requireNonNull(enabled);
        this.enabled = enabled;
    }

    public void disable() {
        this.setEnabled(false);
    }

    public void enable() {
        this.setEnabled(true);
    }

    public boolean isInStock() {
        return this.getQuantityInStock() != null && this.getQuantityInStock() > 0;
    }

    private void setId(UUID id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    private void setQuantityInStock(Integer quantityInStock) {
        Objects.requireNonNull(quantityInStock);
        if (quantityInStock < 0) {
            throw new IllegalArgumentException();
        }
        this.quantityInStock = quantityInStock;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public Long getVersion() {
        return version;
    }

    public OffsetDateTime getAddedAt() {
        return addedAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UUID getCreatedByUserId() {
        return createdByUserId;
    }

    public UUID getLastModifiedByUserId() {
        return lastModifiedByUserId;
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private String name;
        private String brand;
        private String description;
        private BigDecimal regularPrice;
        private BigDecimal salePrice;
        private Boolean enabled;

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder regularPrice(BigDecimal regularPrice) {
            this.regularPrice = regularPrice;
            return this;
        }
        public ProductBuilder salePrice(BigDecimal salePrice) {
            this.salePrice = salePrice;
            return this;
        }

        public ProductBuilder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Product build() {
            return new Product(name, brand, description, enabled, regularPrice, salePrice);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) {
            return false;
        }
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
