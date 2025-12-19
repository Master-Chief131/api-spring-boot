package com.example.demo.dto;

import java.util.List;

public class ArticuloPage {
    public List<ArticuloDTO> content;
    public int page;
    public int size;
    public int totalElements;
    public int totalPages;

    public ArticuloPage(List<ArticuloDTO> content, int page, int size, int totalElements) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / size);
    }
}
