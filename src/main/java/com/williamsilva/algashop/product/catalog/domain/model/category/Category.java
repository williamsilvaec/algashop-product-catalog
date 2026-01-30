package com.williamsilva.algashop.product.catalog.domain.model.category;

import com.williamsilva.algashop.product.catalog.domain.model.IdGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "categories")
public class Category {

    @Id
    private UUID id;

    private String name;

    private Boolean enabled;

    @Version
    private Long version;

    @CreatedDate
    private OffsetDateTime createdAt;

    @LastModifiedDate
    private OffsetDateTime updatedAt;

    @CreatedBy
    private UUID createdByUserId;

    @LastModifiedBy
    private UUID lastModifiedByUserId;

    protected Category() {}

    public Category(String name, Boolean enabled) {
        this.id = IdGenerator.generateTimeBasedUUID();
        this.setName(name);
        this.setEnabled(enabled);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Long getVersion() {
        return version;
    }

    public UUID getCreatedByUserId() {
        return createdByUserId;
    }

    public UUID getLastModifiedByUserId() {
        return lastModifiedByUserId;
    }

    public void setName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public void setEnabled(Boolean enabled) {
        Objects.requireNonNull(enabled);
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category category)) return false;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
