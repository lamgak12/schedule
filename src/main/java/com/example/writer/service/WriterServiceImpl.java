package com.example.writer.service;

import com.example.writer.dto.WriterRequestDto;
import com.example.writer.dto.WriterResponseDto;
import com.example.writer.entity.Writer;
import com.example.writer.repository.WriterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WriterServiceImpl implements WriterService{
    private final WriterRepository writerRepository;

    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Transactional
    @Override
    public WriterResponseDto saveWriter(WriterRequestDto requestDto) {
        Writer writer = new Writer(requestDto.getName(), requestDto.getEmail());
        return writerRepository.saveWriter(writer);
    }
}
