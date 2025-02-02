package com.example.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long writer_id;
    private String password;
    private String contents;
}