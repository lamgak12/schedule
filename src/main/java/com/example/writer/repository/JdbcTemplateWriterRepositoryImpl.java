package com.example.writer.repository;

import com.example.writer.dto.WriterResponseDto;
import com.example.writer.entity.Writer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcTemplateWriterRepositoryImpl implements WriterRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateWriterRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public WriterResponseDto saveWriter(Writer writer) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("writers")
                .usingGeneratedKeyColumns("id");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", writer.getName());
        parameters.put("email", writer.getEmail());
        parameters.put("created_at", now);  // 현재 시간으로 명시적 설정
        parameters.put("updated_at", now);  // 현재 시간으로 명시적 설정

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        Writer savedWriter = new Writer(
                key.longValue(),
                writer.getName(),
                writer.getEmail(),
                writer.getCreatedAt(),
                writer.getUpdatedAt()
        );
        return new WriterResponseDto(savedWriter);
    }
}
