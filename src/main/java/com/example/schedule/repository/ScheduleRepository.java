package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule); // 스케쥴 생성
    List<ScheduleResponseDto> findAllSchedules(); // 전체 스케쥴 조회
    Schedule findScheduleByIdOrElseThrow(Long id); //id로 스케쥴 확인
    int updateSchedule(Long id, Long writer_id, String contents);  // 작성자, 내용 수정
    int deleteSchedule(Long id);  // id로 스케쥴 삭제
}
