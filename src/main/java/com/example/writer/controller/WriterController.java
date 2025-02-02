package com.example.writer.controller;

import com.example.writer.dto.WriterRequestDto;
import com.example.writer.dto.WriterResponseDto;
import com.example.writer.service.WriterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
