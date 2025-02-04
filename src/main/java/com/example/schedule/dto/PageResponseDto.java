package com.example.schedule.dto;

import java.util.List;

public class PageResponseDto{
    private List<ScheduleResponseDto> content;
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalElements;

    public PageResponseDto(List<ScheduleResponseDto> content, int currentPage, int pageSize, long totalElements) {
        this.content = content;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / pageSize);
    }

    public List<ScheduleResponseDto> getContent() {
        return content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }
}
