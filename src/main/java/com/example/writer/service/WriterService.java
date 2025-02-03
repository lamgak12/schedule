package com.example.writer.service;

import com.example.writer.dto.WriterRequestDto;
import com.example.writer.dto.WriterResponseDto;
import com.example.writer.dto.WriterWithSchedulesResponseDto;

import java.util.List;

public interface WriterService {
    WriterResponseDto saveWriter(WriterRequestDto requestDto); // 작성자 생성
    List<WriterResponseDto> findAllWriters();  // 전체 작성자 조회
    WriterResponseDto findWriterByIdOrElseThrow(Long id); // id로 작성자 확인
    WriterWithSchedulesResponseDto findWriterWithSchedulesById(Long id);
    WriterResponseDto updateName(Long id, String name); // 작성자 이름 수정
    void deleteWriter(Long id);  // id로 작성자 삭제

}
