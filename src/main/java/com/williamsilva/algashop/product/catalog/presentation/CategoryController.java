package com.williamsilva.algashop.product.catalog.presentation;

import com.williamsilva.algashop.product.catalog.application.category.management.CategoryInput;
import com.williamsilva.algashop.product.catalog.application.category.management.CategoryManagementService;
import com.williamsilva.algashop.product.catalog.application.category.query.CategoryDetailOutput;
import com.williamsilva.algashop.product.catalog.application.category.query.CategoryQueryService;
import com.williamsilva.algashop.product.catalog.application.product.query.PageModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryQueryService categoryQueryService;
    private final CategoryManagementService categoryManagementService;

    public CategoryController(CategoryQueryService categoryQueryService,
                              CategoryManagementService categoryManagementService) {
        this.categoryQueryService = categoryQueryService;
        this.categoryManagementService = categoryManagementService;
    }

    @GetMapping
    public PageModel<CategoryDetailOutput> filter(@RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer size) {
        return categoryQueryService.filter(size,page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDetailOutput create(@RequestBody @Valid CategoryInput input) {
        UUID categoryId = categoryManagementService.create(input);
        return categoryQueryService.findById(categoryId);
    }

    @GetMapping("/{categoryId}")
    public CategoryDetailOutput findById(@PathVariable UUID categoryId) {
        return categoryQueryService.findById(categoryId);
    }

    @PutMapping("/{categoryId}")
    public CategoryDetailOutput update(
            @PathVariable UUID categoryId,
            @RequestBody @Valid CategoryInput input) {
        categoryManagementService.update(categoryId, input);
        return categoryQueryService.findById(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable UUID categoryId) {
        categoryManagementService.disable(categoryId);
    }
}
