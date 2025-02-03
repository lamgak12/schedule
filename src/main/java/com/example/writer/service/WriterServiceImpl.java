package com.example.writer.service;

import com.example.writer.dto.WriterRequestDto;
import com.example.writer.dto.WriterResponseDto;
import com.example.writer.entity.Writer;
import com.example.writer.repository.WriterRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class WriterServiceImpl implements WriterService{
    private final WriterRepository writerRepository;

    @Override
    public WriterResponseDto saveWriter(WriterRequestDto requestDto) {
        Writer writer = new Writer(requestDto.getName(), requestDto.getEmail());
        return writerRepository.saveWriter(writer);
    }

    @Override
    public List<WriterResponseDto> findAllWriters() {
        return writerRepository.findAllWriters();
    }

    @Override
    public WriterResponseDto findWriterById(Long id) {
        return writerRepository.findWriterByIdOrElseThrow(id);
    }

    @Override
    public WriterResponseDto updateName(Long id, String name) {
        // 1. 작성자 존재 여부 확인
        writerRepository.findWriterByIdOrElseThrow(id);

        // 2. 작성자 이름 검증
        if (name == null || name.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이름은 필수 입력 값입니다.");
        }

        // 3. 작성자 업데이트
        int updatedRows = writerRepository.updateName(id, name);
        if (updatedRows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "작성자를 찾을 수 없습니다.");
        }

        // 4. 업데이트된 작성자 정보 반환
        return writerRepository.findWriterByIdOrElseThrow(id);
    }
}
