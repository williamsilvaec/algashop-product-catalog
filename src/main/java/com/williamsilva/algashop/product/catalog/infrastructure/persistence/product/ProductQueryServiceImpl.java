package com.williamsilva.algashop.product.catalog.infrastructure.persistence.product;

import com.williamsilva.algashop.product.catalog.application.product.query.PageModel;
import com.williamsilva.algashop.product.catalog.application.product.query.ProductDetailOutput;
import com.williamsilva.algashop.product.catalog.application.product.query.ProductQueryService;
import com.williamsilva.algashop.product.catalog.domain.model.product.Product;
import com.williamsilva.algashop.product.catalog.domain.model.product.ProductNotFoundException;
import com.williamsilva.algashop.product.catalog.domain.model.product.ProductRepository;
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
    public PageModel<ProductDetailOutput> filter(Integer size, Integer number) {
        return null;
    }

    private ProductDetailOutput convertToOutput(Product product) {
        ProductDetailOutput output = new ProductDetailOutput();
        output.setId(product.getId());
        output.setAddedAt(product.getAddedAt());
        output.setName(product.getName());
        output.setBrand(product.getBrand());
        output.setRegularPrice(product.getRegularPrice());
        output.setSalePrice(product.getSalePrice());
        output.setInStock(product.isInStock());
        output.setEnabled(product.getEnabled());
        output.setDescription(product.getDescription());
        return output;
    }
}
