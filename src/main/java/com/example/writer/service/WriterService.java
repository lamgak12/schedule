package com.example.writer.service;

import com.example.writer.dto.WriterRequestDto;
import com.example.writer.dto.WriterResponseDto;

public interface WriterService {
    WriterResponseDto saveWriter(WriterRequestDto requestDto);
}
