package com.williamsilva.algashop.product.catalog.infrastructure.persistence.product;

import com.williamsilva.algashop.product.catalog.application.product.query.CategoryMinimalOutput;
import com.williamsilva.algashop.product.catalog.application.product.query.PageModel;
import com.williamsilva.algashop.product.catalog.application.product.query.ProductDetailOutput;
import com.williamsilva.algashop.product.catalog.application.product.query.ProductFilter;
import com.williamsilva.algashop.product.catalog.application.product.query.ProductQueryService;
import com.williamsilva.algashop.product.catalog.application.product.query.ProductSummaryOutput;
import com.williamsilva.algashop.product.catalog.domain.model.category.Category;
import com.williamsilva.algashop.product.catalog.domain.model.product.Product;
import com.williamsilva.algashop.product.catalog.domain.model.product.ProductNotFoundException;
import com.williamsilva.algashop.product.catalog.domain.model.product.ProductRepository;
import com.williamsilva.algashop.product.catalog.infrastructure.util.Slugfier;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.AggregationExpressionCriteria;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private static final String findWordRegex = "(?i)%s";

    private final ProductRepository productRepository;
    private final MongoOperations mongoOperations;

    public ProductQueryServiceImpl(ProductRepository productRepository, MongoOperations mongoOperations) {
        this.productRepository = productRepository;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public ProductDetailOutput findById(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        return convertToOutput(product);
    }

    @Override
    public PageModel<ProductSummaryOutput> filter(ProductFilter filter) {
        Query query = queryWith(filter);
        long totalItems = mongoOperations.count(query, Product.class);
        Sort sort = sortWith(filter);

        PageRequest pageRequest = PageRequest.of(filter.getPage(), filter.getSize(), sort);
        Query pagedQuery = query.with(pageRequest);

        List<Product> products;
        int totalPages = 0;

        if (totalItems > 0) {
            products = mongoOperations.find(pagedQuery, Product.class);
            totalPages = (int) Math.ceil((double) totalItems / pageRequest.getPageSize());
        } else {
            products = new ArrayList<>();
        }

        List<ProductSummaryOutput> productOutputs = products.stream()
                .map(this::convertToSummaryOutput)
                .collect(Collectors.toList());

        PageModel<ProductSummaryOutput> pageModel = new PageModel<>();
        pageModel.setContent(productOutputs);
        pageModel.setNumber(pageRequest.getPageNumber());
        pageModel.setSize(pageRequest.getPageSize());
        pageModel.setTotalElements(totalItems);
        pageModel.setTotalPages(totalPages);
        return pageModel;
    }

    private ProductDetailOutput convertToOutput(Product product) {
        ProductDetailOutput output = new ProductDetailOutput();
        output.setId(product.getId());
        output.setAddedAt(product.getAddedAt());
        output.setName(product.getName());
        output.setBrand(product.getBrand());
        output.setRegularPrice(product.getRegularPrice());
        output.setCategory(convertToCategoryMinimalOutput(product.getCategory()));
        output.setSalePrice(product.getSalePrice());
        output.setInStock(product.isInStock());
        output.setEnabled(product.getEnabled());
        output.setDescription(product.getDescription());
        output.setQuantityInStock(product.getQuantityInStock());
        output.setHasDiscount(product.getHasDiscount());
        output.setDiscountPercentageRounded(product.getDiscountPercentageRounded());

        output.setSlug(Slugfier.slugify(product.getName()));

        return output;
    }

    private ProductSummaryOutput convertToSummaryOutput(Product product) {
        ProductSummaryOutput output = new ProductSummaryOutput();
        output.setId(product.getId());
        output.setAddedAt(product.getAddedAt());
        output.setName(product.getName());
        output.setCategory(convertToCategoryMinimalOutput(product.getCategory()));
        output.setBrand(product.getBrand());
        output.setRegularPrice(product.getRegularPrice());
        output.setSalePrice(product.getSalePrice());
        output.setInStock(product.isInStock());
        output.setEnabled(product.getEnabled());

        output.setShortDescription(StringUtils.abbreviate(product.getDescription(), 15));

        output.setQuantityInStock(product.getQuantityInStock());
        output.setHasDiscount(product.getHasDiscount());
        output.setDiscountPercentageRounded(product.getDiscountPercentageRounded());

        output.setSlug(Slugfier.slugify(product.getName()));

        return output;
    }

    private CategoryMinimalOutput convertToCategoryMinimalOutput(Category category) {
        CategoryMinimalOutput output = new CategoryMinimalOutput();
        output.setId(category.getId());
        output.setName(category.getName());
        return output;
    }

    private Sort sortWith(ProductFilter filter) {
        return Sort.by(filter.getSortDirectionOrDefault(),
                filter.getSortByPropertyOrDefault().getPropertyName());
    }

    private Query queryWith(ProductFilter filter) {
        Query query = new Query();

        if (filter.getEnabled() != null) {
            query.addCriteria(Criteria.where("enabled").is(filter.getEnabled()));
        }

        if (filter.getAddedAtFrom() != null && filter.getAddedAtTo() != null) {
            query.addCriteria(Criteria.where("addedAt")
                    .gte(filter.getAddedAtFrom())
                    .lte(filter.getAddedAtTo())
            );
        } else {
            if (filter.getAddedAtFrom() != null) {
                query.addCriteria(Criteria.where("addedAt").gte(filter.getAddedAtFrom()));
            } else if (filter.getAddedAtTo() != null) {
                query.addCriteria(Criteria.where("addedAt").lte(filter.getAddedAtTo()));
            }
        }

        if (filter.getPriceFrom() != null && filter.getPriceTo() != null ) {
            query.addCriteria(Criteria.where("salePrice")
                    .gte(filter.getPriceFrom())
                    .lte(filter.getPriceTo())
            );
        } else {
            if (filter.getPriceFrom() != null) {
                query.addCriteria(Criteria.where("salePrice").gte(filter.getPriceFrom()));
            } else if (filter.getPriceTo() != null) {
                query.addCriteria(Criteria.where("salePrice").lte(filter.getPriceTo()));
            }
        }

        if (filter.getHasDiscount() != null) {
            if (filter.getHasDiscount()) {
                query.addCriteria(AggregationExpressionCriteria.whereExpr(
                        ComparisonOperators.valueOf("$salePrice")
                                .lessThan("$regularPrice")
                ));
            } else {
                query.addCriteria(AggregationExpressionCriteria.whereExpr(
                        ComparisonOperators.valueOf("$salePrice")
                                .equalTo("$regularPrice")
                ));
            }
        }

        if (filter.getInStock() != null) {
            if (filter.getInStock()) {
                query.addCriteria(Criteria.where("quantityInStock").gt(0));
            } else {
                query.addCriteria(Criteria.where("quantityInStock").is(0));
            }
        }

        if (filter.getCategoriesId() != null && filter.getCategoriesId().length > 0) {
            query.addCriteria(Criteria.where("categoryId").in(
                    (Object[]) filter.getCategoriesId()
            ));
        }

        if (StringUtils.isNotBlank(filter.getTerm())) {
            String regexExpression = String.format(findWordRegex, filter.getTerm());
            query.addCriteria(
                    new Criteria().orOperator(
                            Criteria.where("name").regex(regexExpression),
                            Criteria.where("brand").regex(regexExpression),
                            Criteria.where("description").regex(regexExpression)
                    )
            );
        }

        return query;
    }
}
