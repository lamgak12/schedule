package com.example.writer.dto;

import com.example.writer.entity.Writer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class WriterResponseDto {
    private Long id;
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public WriterResponseDto(Writer writer) {
        this.id = writer.getId();
        this.name = writer.getName();
        this.createdAt = writer.getCreatedAt();
        this.updatedAt = writer.getUpdatedAt();
    }
}