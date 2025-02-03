package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRequestDto {
    private Long writerId;  // 작성자의 ID
    private String password;
    private String contents;
}