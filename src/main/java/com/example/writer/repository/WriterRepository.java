package com.example.writer.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.writer.dto.WriterResponseDto;
import com.example.writer.entity.Writer;

import java.util.List;

public interface WriterRepository {
    WriterResponseDto saveWriter(Writer writer);
    List<WriterResponseDto> findAllWriters(); // 전체 작성자 조회
    WriterResponseDto findWriterByIdOrElseThrow(Long id);

}
