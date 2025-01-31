package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long id;
    private String writer;
    private String password;
    private String contents;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Schedule(String writer, String password, String contents) {
        this.writer = writer;
        this.password = password;
        this.contents = contents;
        this.createdAt = new Timestamp(System.currentTimeMillis());  // createdAt 초기화
        this.updatedAt = new Timestamp(System.currentTimeMillis());  // updatedAt 초기화
    }
}
