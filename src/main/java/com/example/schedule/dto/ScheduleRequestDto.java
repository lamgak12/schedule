package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String writer;
    private String password;
    private String contents;
}