package com.williamsilva.algashop.product.catalog.presentation;

import com.williamsilva.algashop.product.catalog.application.product.management.ProductInput;
import com.williamsilva.algashop.product.catalog.application.product.management.ProductManagementApplicationService;
import com.williamsilva.algashop.product.catalog.application.product.query.PageModel;
import com.williamsilva.algashop.product.catalog.application.product.query.ProductDetailOutput;
import com.williamsilva.algashop.product.catalog.application.product.query.ProductQueryService;
import com.williamsilva.algashop.product.catalog.domain.model.category.CategoryNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductManagementApplicationService productManagementApplicationService;
    private final ProductQueryService productQueryService;

    public ProductController(ProductManagementApplicationService productManagementApplicationService,
                             ProductQueryService productQueryService) {
        this.productManagementApplicationService = productManagementApplicationService;
        this.productQueryService = productQueryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDetailOutput create(@RequestBody @Valid ProductInput input) {
        UUID productId;
        try {
            productId = productManagementApplicationService.create(input);
        } catch (CategoryNotFoundException e) {
            throw new UnprocessableContentException(e.getMessage(), e);
        }

        return productQueryService.findById(productId);
    }

    @GetMapping("/{productId}")
    public ProductDetailOutput findById(@PathVariable UUID productId) {
        return productQueryService.findById(productId);
    }

    @GetMapping
    public PageModel<ProductDetailOutput> filter(
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "number", required = false) Integer number
    ) {
        return productQueryService.filter(size, number);
    }
}
