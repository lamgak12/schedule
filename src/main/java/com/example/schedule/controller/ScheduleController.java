package com.example.schedule.controller;

import com.example.schedule.dto.PageResponseDto;
import com.example.schedule.dto.ScheduleCreateRequestDto;
import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleCreateRequestDto requestDto){
        ScheduleResponseDto responseDto = scheduleService.saveSchedule(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<PageResponseDto<ScheduleResponseDto>> findAllSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        PageResponseDto<ScheduleResponseDto> response = scheduleService.findAllSchedules(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 단일 일정 조회(id)
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){
        ScheduleResponseDto responseDto = scheduleService.findScheduleById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 일정 수정(id)
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, requestDto.getPassword(), requestDto.getWriter(), requestDto.getContents());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 일정 삭제(id)
    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> deleteSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto){
        scheduleService.deleteSchedule(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}