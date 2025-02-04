package com.example.schedule.repository;

import com.example.exception.ScheduleNotFoundException;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto save(Schedule schedule) {
        //save는 엔티티 자체를 반환하는 것이 관례.. JPA뿐만 아니라 다른 ORM도 마찬가지, 데이터를 사용하는 주체는 서비스로직에서 판단
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedules").usingGeneratedKeyColumns("id");

        Timestamp now = new Timestamp(System.currentTimeMillis());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer_id", schedule.getWriter_id());
        parameters.put("password", schedule.getPassword());
        parameters.put("contents", schedule.getContents());
        parameters.put("created_at", now);  // 현재 시간으로 명시적 설정
        parameters.put("updated_at", now);  // 현재 시간으로 명시적 설정

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        Schedule savedSchedule = new Schedule(
                key.longValue(),
                schedule.getWriter_id(),
                schedule.getPassword(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
        return new ScheduleResponseDto(savedSchedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(int size, int offset) {
        String sql = "select * from schedules order by created_at desc limit ? offset ?";
        return jdbcTemplate.query(sql, new Object[]{size, offset}, scheduleRowMapperV1());
    }

    @Override
    public long countSchedules() {
        String sql = "select count(*) from schedules";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedules where id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ScheduleNotFoundException("일정이 존재하지 않습니다."));
    }

    @Override
    public List<ScheduleResponseDto> findSchedulesByWriterId(Long writerId) {
        String sql = "select * from schedules where writer_id = ?";
        return jdbcTemplate.query(sql, scheduleRowMapperV1(), writerId);
    }

    @Override
    public int updateContents(Long id, String contents) {
        return jdbcTemplate.update("update schedules set contents = ? where id = ?", contents, id);
    }

    @Override
    public int deleteSchedule(Long id) {
        return jdbcTemplate.update("delete from schedules where id = ?", id);
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapperV1() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getLong("writer_id"),
                        rs.getString("contents"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        };
    }
    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getLong("writer_id"),
                        rs.getString("password"),
                        rs.getString("contents"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        };
    }
}
