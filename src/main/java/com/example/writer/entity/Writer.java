package com.example.writer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class Writer {
    private Long id;
    private String name;
    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Writer(String name, String email) {
        this.name = name;
        this.email = email;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
