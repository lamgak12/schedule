package com.example.writer.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.writer.dto.WriterResponseDto;
import com.example.writer.entity.Writer;

import java.util.List;

public interface WriterRepository {
    WriterResponseDto saveWriter(Writer writer); // 작성자 생성
    List<WriterResponseDto> findAllWriters(); // 전체 작성자 조회
    WriterResponseDto findWriterByIdOrElseThrow(Long id); // id로 작성자 조회
    int updateName(Long id, String name);  // 작성자, 내용 수정

}
