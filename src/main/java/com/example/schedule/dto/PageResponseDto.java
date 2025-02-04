package com.example.schedule.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class PageResponseDto<T> {
    private List<T> content;
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalElements;

    public PageResponseDto(List<T> content, int currentPage, int pageSize, long totalElements) {
        this.content = content;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / pageSize);
    }
}