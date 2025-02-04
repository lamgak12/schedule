package com.example.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRequestDto {
    @NotBlank(message = "작성자 ID는 필수 입력 값입니다.")
    private Long writerId;  // 작성자의 ID
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
    @NotBlank(message = "내용은 필수 입력 값입니다.")
    @Size(max = 200, message = "내용은 최대 200자까지 입력 가능합니다.")
    private String contents;
}