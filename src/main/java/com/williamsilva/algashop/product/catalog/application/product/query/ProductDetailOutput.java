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
}
