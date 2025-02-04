package com.example.writer.repository;

import com.example.exception.UserNotFoundException;
import com.example.writer.dto.WriterResponseDto;
import com.example.writer.entity.Writer;
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

    @Override
    public List<WriterResponseDto> findAllWriters() {
        return jdbcTemplate.query("select id, name, created_at, updated_at from writers", writerRowMapperV1());
    }

    @Override
    public WriterResponseDto findWriterByIdOrElseThrow(Long id) {
        List<WriterResponseDto> result = jdbcTemplate.query("select id, name, created_at, updated_at from writers where id = ?", writerRowMapperV1(), id);
        return result.stream().findAny().orElseThrow(() -> new UserNotFoundException("작성자가 존재하지 않습니다."));
    }

    @Override
    public int updateName(Long id, String name) {
        return jdbcTemplate.update("update writers set name = ? where id = ?", name, id);
    }

    @Override
    public int deleteWriter(Long id) {
        return jdbcTemplate.update("delete from writers where id = ?", id);
    }

    private RowMapper<WriterResponseDto> writerRowMapperV1() { // 이름이 좀더 직관적이였으면 좋겠다..
        return new RowMapper<WriterResponseDto>() {
            @Override
            public WriterResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new WriterResponseDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        };
    }
}