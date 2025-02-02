package com.example.writer.controller;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.writer.dto.WriterRequestDto;
import com.example.writer.dto.WriterResponseDto;
import com.example.writer.service.WriterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/writers")
public class WriterController {
    private final WriterService writerService;

    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    @PostMapping
    public ResponseEntity<WriterResponseDto> createWriter(@RequestBody WriterRequestDto requestDto){
        return new ResponseEntity<>(writerService.saveWriter(requestDto), HttpStatus.CREATED); // findScheduleById처럼 분리
    }

    // 단일 작성자 조회(id)
    @GetMapping("/{id}")
    public ResponseEntity<WriterResponseDto> findWriterById(@PathVariable Long id){
        WriterResponseDto responseDto = writerService.findWriterById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
