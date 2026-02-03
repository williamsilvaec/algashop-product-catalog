package com.williamsilva.algashop.product.catalog.application.utility;

public class PageFilter {

    private int size = 15;
    private int page = 0;

    public PageFilter() {}

    public PageFilter(int size, int page) {
        this.size = size;
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public int getPage() {
        return page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
