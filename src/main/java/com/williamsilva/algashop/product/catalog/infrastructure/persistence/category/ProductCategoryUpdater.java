package com.williamsilva.algashop.product.catalog.infrastructure.persistence.category;

import com.williamsilva.algashop.product.catalog.application.category.event.CategoryUpdatedEvent;
import com.williamsilva.algashop.product.catalog.domain.model.product.Product;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryUpdater {

    private final MongoOperations mongoOperations;

    public ProductCategoryUpdater(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public void copyCategoryDataToProducts(CategoryUpdatedEvent event) {
        Query query = new Query(
                Criteria.where("category.id").is(event.getCategoryId())
        );

        Update update = new Update()
                .set("category.name", event.getName())
                .set("category.enabled", event.getEnabled());

        mongoOperations.updateMulti(query, update, Product.class);
    }
}
