package com.williamsilva.algashop.product.catalog.infrastructure.listener.category;

import com.williamsilva.algashop.product.catalog.application.category.event.CategoryUpdatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CategoryEventListener {

    private static final Logger log = LoggerFactory.getLogger(CategoryEventListener.class);

    @EventListener
    public void handle(CategoryUpdatedEvent event) {
        log.info("Category updated received: {}", event.getCategoryId());
    }
}
