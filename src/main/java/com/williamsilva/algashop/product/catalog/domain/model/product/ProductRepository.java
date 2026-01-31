package com.williamsilva.algashop.product.catalog.domain.model.product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
