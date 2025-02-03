package com.example.writer.service;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.writer.dto.WriterRequestDto;
import com.example.writer.dto.WriterResponseDto;
import com.example.writer.entity.Writer;

import java.util.List;

public interface WriterService {
    WriterResponseDto saveWriter(WriterRequestDto requestDto); // 작성자 생성
    List<WriterResponseDto> findAllWriters();  // 전체 작성자 조회
    WriterResponseDto findWriterById(Long id); // id로 스케쥴 확인
    WriterResponseDto updateName(Long id, String name);
}
