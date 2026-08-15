package com.williamsilva.algashop.product.catalog.infrastructure.listener.category;

import com.williamsilva.algashop.product.catalog.application.category.event.CategoryUpdatedEvent;
import com.williamsilva.algashop.product.catalog.infrastructure.persistence.category.ProductCategoryUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CategoryEventListener {

    private static final Logger log = LoggerFactory.getLogger(CategoryEventListener.class);

    private final ProductCategoryUpdater productCategoryUpdater;

    public CategoryEventListener(ProductCategoryUpdater productCategoryUpdater) {
        this.productCategoryUpdater = productCategoryUpdater;
    }

    @EventListener
    @Async
    public void handle(CategoryUpdatedEvent event) {
        log.info("Category updated received: {}", event.getCategoryId());
        productCategoryUpdater.copyCategoryDataToProducts(event);
    }
}
