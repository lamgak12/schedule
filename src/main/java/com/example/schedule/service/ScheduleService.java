package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto); // 스케쥴 생성
    List<ScheduleResponseDto> findAllSchedules();  // 전체 스케줄 조회
    ScheduleResponseDto findScheduleById(Long id); // id로 스케쥴 확인
    // 작성자, 내용 수정
    ScheduleResponseDto updateSchedule(Long id, String password, String writer, String contents);
    void deleteSchedule(Long id, String password); // id로 스케쥴 삭제
}
