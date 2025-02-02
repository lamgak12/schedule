package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule); // 스케쥴 생성
    List<ScheduleResponseDto> findAllSchedules(); // 전체 스케쥴 조회
    ScheduleResponseDto findScheduleByIdOrElseThrow(Long id); //id로 스케쥴 확인
}
