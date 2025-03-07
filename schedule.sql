CREATE TABLE schedules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 관리 식별자',
    name VARCHAR(100) NOT NULL COMMENT '작성자 이름',
    password VARCHAR(100) NOT NULL COMMENT '게시글 비밀번호 (수정/삭제 시 필요)',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '게시글 생성일',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '게시글 수정일',
    contents TEXT NOT NULL COMMENT '할 일 내용'
) COMMENT='일정 관리 테이블';
alter table schedules rename column name to writer;