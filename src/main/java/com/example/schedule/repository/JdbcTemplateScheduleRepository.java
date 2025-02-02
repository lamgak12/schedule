package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedules").usingGeneratedKeyColumns("id");

        Timestamp now = new Timestamp(System.currentTimeMillis());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer", schedule.getWriter());
        parameters.put("password", schedule.getPassword());
        parameters.put("contents", schedule.getContents());
        parameters.put("created_at", now);  // 현재 시간으로 명시적 설정
        parameters.put("updated_at", now);  // 현재 시간으로 명시적 설정

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        Schedule savedSchedule = new Schedule(
                key.longValue(),
                schedule.getWriter(),
                schedule.getPassword(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
        return new ScheduleResponseDto(savedSchedule);
    }
}
