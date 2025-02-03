package com.example.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String writer;
    private String password;
    private String contents;
}