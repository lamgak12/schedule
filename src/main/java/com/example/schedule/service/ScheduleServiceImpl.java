package com.example.schedule.service;

import com.example.schedule.dto.ScheduleCreateRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import com.example.writer.dto.WriterResponseDto;
import com.example.writer.dto.WriterWithSchedulesResponseDto;
import com.example.writer.repository.WriterRepository;
import com.example.writer.service.WriterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final WriterService writerService;

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleCreateRequestDto requestDto) {
        WriterResponseDto writer = writerService.findWriterByIdOrElseThrow(requestDto.getWriterId());
        Schedule schedule = new Schedule(writer.getId(), requestDto.getPassword(), requestDto.getContents());
        //여기서 dto로 감싸야함..
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAllSchedules();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, String password, String writer, String contents) {
        // 1. 일정 조회
        Schedule updatedSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        // 2. 비밀번호 확인
        if (!checkPassword(id, password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        // 3. 작성자 이름 변경 (MySQL에서 `ON UPDATE CASCADE` 처리)
        writerService.updateName(updatedSchedule.getWriter_id(), writer);

        // 4. 일정 업데이트
        int updatedRows = scheduleRepository.updateContents(id, contents);
        if (updatedRows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다.");
        }

        // 5. 업데이트된 일정 반환
        return new ScheduleResponseDto(updatedSchedule);
    }

    @Override
    public void deleteSchedule(Long id, String password) {
        // 1. 비밀번호 확인
        if (!checkPassword(id, password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // 2. 일정 삭제
        int deletedRows = scheduleRepository.deleteSchedule(id);
        if (deletedRows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다.");
        }
    }

    private boolean checkPassword(Long id, String password) {
        // 1. 주어진 id로 Schedule 조회
        Schedule existingSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        // 2. 비밀번호 확인
        return existingSchedule.getPassword().equals(password);
    }

}
