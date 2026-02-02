package com.williamsilva.algashop.product.catalog.application.product.query;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PageModel<T> {

    private int number;
    private int size;
    private int totalPages;
    private long totalElements;

    private List<T> content = new ArrayList<>();

    public static <T> PageModel<T> of(Page<T> page) {
        PageModel<T> pageModel = new PageModel<>();
        pageModel.number = page.getNumber();
        pageModel.size = page.getSize();
        pageModel.totalPages = page.getTotalPages();
        pageModel.totalElements = page.getTotalElements();
        pageModel.content = page.getContent();
        return pageModel;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PageModel<?> pageModel)) {
            return false;
        }

        return number == pageModel.number
                && size == pageModel.size
                && totalPages == pageModel.totalPages
                && totalElements == pageModel.totalElements
                && Objects.equals(content, pageModel.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, size, totalPages, totalElements, content);
    }
}
