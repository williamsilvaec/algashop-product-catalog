package com.williamsilva.algashop.product.catalog.domain.model.product;

import com.williamsilva.algashop.product.catalog.infrastructure.persistence.MongoConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataMongoTest
@Import(MongoConfig.class)
class ProductRepositoryIT {

    private static final Logger log = LoggerFactory.getLogger(ProductRepositoryIT.class);

    @Autowired
    private ProductRepository repository;

    @Test
    void shouldFilter() {
        Page<ProductNameProjection> products = repository.findAllByEnabled(true, PageRequest.of(0, 3));
        products.forEach(p -> log.info("Product - Id: {} Name: {}", p.id(), p.name()));
    }
}