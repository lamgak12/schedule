package com.example.writer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class WriterRequestDto {
    @NotBlank(message = "이름 필수 입력 값입니다.")
    private String name;
    @Email
    @NotBlank(message = "이메일은 필수 입력값 입니다.")
    private String email;
}
