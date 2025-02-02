package com.example.writer.repository;

import com.example.writer.dto.WriterResponseDto;
import com.example.writer.entity.Writer;

public interface WriterRepository {
    WriterResponseDto saveWriter(Writer writer);
    Writer findWriterByIdOrElseThrow(Long id);
}
