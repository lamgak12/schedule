package com.example.writer.service;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.writer.dto.WriterRequestDto;
import com.example.writer.dto.WriterResponseDto;

public interface WriterService {
    WriterResponseDto saveWriter(WriterRequestDto requestDto);
    WriterResponseDto findWriterById(Long id); // id로 스케쥴 확인
}
