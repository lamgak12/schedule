package com.example.writer.controller;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.writer.dto.WriterRequestDto;
import com.example.writer.dto.WriterResponseDto;
import com.example.writer.dto.WriterWithSchedulesResponseDto;
import com.example.writer.service.WriterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/writers")
public class WriterController {
    private final WriterService writerService;

    // 작성자 생성
    @PostMapping
    public ResponseEntity<WriterResponseDto> createWriter(@RequestBody WriterRequestDto requestDto){
        return new ResponseEntity<>(writerService.saveWriter(requestDto), HttpStatus.CREATED); // update + insert = upsert로 동작해야 완전함..
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<WriterResponseDto>> findAllWriters(){
        List<WriterResponseDto> responseDtos = writerService.findAllWriters();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    // 단일 작성자 조회(id)
    @GetMapping("/{id}")
    public ResponseEntity<WriterWithSchedulesResponseDto> findWriterById(@PathVariable Long id){
        WriterWithSchedulesResponseDto responseDto = writerService.findWriterWithSchedulesById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 작성자 삭제(id)
    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> deleteSchedule(@PathVariable Long id){
        writerService.deleteWriter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
