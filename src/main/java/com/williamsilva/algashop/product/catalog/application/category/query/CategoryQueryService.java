package com.williamsilva.algashop.product.catalog.application.category.query;

import com.williamsilva.algashop.product.catalog.application.product.query.PageModel;

import java.util.UUID;

public interface CategoryQueryService {

    PageModel<CategoryDetailOutput> filter(Integer size, Integer number);

    CategoryDetailOutput findById(UUID categoryId);
}
