package com.williamsilva.algashop.product.catalog.application.category.query;

import com.williamsilva.algashop.product.catalog.application.PageModel;

import java.util.UUID;

public interface CategoryQueryService {

    PageModel<CategoryDetailOutput> filter(CategoryFilter filter);

    CategoryDetailOutput findById(UUID categoryId);
}
