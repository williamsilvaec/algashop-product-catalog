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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDetailOutput findById(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        return convertToOutput(product);
    }

    @Override
    public PageModel<ProductSummaryOutput> filter(ProductFilter filter) {
        Page<Product> products = productRepository.findAll(PageRequest.of(filter.getPage(), filter.getSize()));
        Page<ProductSummaryOutput> productSummaryOutputPage = products.map(this::convertToSummaryOutput);
        return PageModel.of(productSummaryOutputPage);
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
}
