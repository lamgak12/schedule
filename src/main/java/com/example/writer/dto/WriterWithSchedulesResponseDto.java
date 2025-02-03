package com.example.writer.dto;

import com.example.schedule.dto.ScheduleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@AllArgsConstructor
public class WriterWithSchedulesResponseDto {
    private Long id;
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<ScheduleResponseDto> schedules;
}
