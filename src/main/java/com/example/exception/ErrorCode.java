package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
public enum ErrorCode {
    // 📌 스케줄 관련 예외
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 일정 정보를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),

    // 📌 작성자(User) 관련 예외
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 작성자를 찾을 수 없습니다."),

    // 📌 공통 예외
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String message;
}
