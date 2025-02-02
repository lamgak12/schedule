package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long id;
    private Long writer_id;
    private String password;
    private String contents;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Schedule(Long writer_id, String password, String contents) {
        this.writer_id = writer_id;
        this.password = password;
        this.contents = contents;
        this.createdAt = new Timestamp(System.currentTimeMillis());  // createdAt 초기화
        this.updatedAt = new Timestamp(System.currentTimeMillis());  // updatedAt 초기화
    }
}
