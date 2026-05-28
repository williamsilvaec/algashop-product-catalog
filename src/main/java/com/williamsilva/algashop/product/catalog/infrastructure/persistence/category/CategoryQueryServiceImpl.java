package com.williamsilva.algashop.product.catalog.infrastructure.persistence.category;

import com.williamsilva.algashop.product.catalog.application.PageModel;
import com.williamsilva.algashop.product.catalog.application.category.query.CategoryDetailOutput;
import com.williamsilva.algashop.product.catalog.application.category.query.CategoryFilter;
import com.williamsilva.algashop.product.catalog.application.category.query.CategoryQueryService;
import com.williamsilva.algashop.product.catalog.domain.model.category.Category;
import com.williamsilva.algashop.product.catalog.domain.model.category.CategoryNotFoundException;
import com.williamsilva.algashop.product.catalog.domain.model.category.CategoryRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository repository;
    private final MongoOperations mongoOperations;

    public CategoryQueryServiceImpl(CategoryRepository repository, MongoOperations mongoOperations) {
        this.repository = repository;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public PageModel<CategoryDetailOutput> filter(CategoryFilter filter) {
        Query query = queryWith(filter);
        long totalItems = mongoOperations.count(query, Category.class);
        Sort sort = sortWith(filter);

        PageRequest pageRequest = PageRequest.of(filter.getPage(), filter.getSize(), sort);
        Query pagedQuery = query.with(pageRequest);

        List<Category> categorys;
        int totalPages = 0;

        if (totalItems > 0) {
            categorys = mongoOperations.find(pagedQuery, Category.class);
            totalPages = (int) Math.ceil((double) totalItems / pageRequest.getPageSize());
        } else {
            categorys = new ArrayList<>();
        }

        List<CategoryDetailOutput> categoryOutputs = categorys.stream()
                .map(this::convert)
                .collect(Collectors.toList());

        return PageModel.<CategoryDetailOutput>builder()
                .content(categoryOutputs)
                .number(pageRequest.getPageNumber())
                .size(pageRequest.getPageSize())
                .totalElements(totalItems)
                .totalPages(totalPages)
                .build();
    }

    private Sort sortWith(CategoryFilter filter) {
        return Sort.by(filter.getSortDirectionOrDefault(),
                filter.getSortByPropertyOrDefault().getPropertyName());
    }

    private Query queryWith(CategoryFilter filter) {
        Query query = new Query();

        if (filter.getEnabled() != null) {
            query.addCriteria(Criteria.where("enabled").is(filter.getEnabled()));
        }

        if (StringUtils.isNotBlank(filter.getName())) {
            query.addCriteria(Criteria.where("name").regex(filter.getName().trim(), "i"));
        }

        return query;
    }

    @Override
    public CategoryDetailOutput findById(UUID categoryId) {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        return convert(category);
    }

    private CategoryDetailOutput convert(Category category) {
        return new CategoryDetailOutput(category.getId(), category.getName(), category.getEnabled());
    }
}
