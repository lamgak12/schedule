-- 작성자 테이블 생성
CREATE TABLE writers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '작성자 관리 식별자',
    name VARCHAR(100) NOT NULL COMMENT '작성자 이름',
    email VARCHAR(100) NOT NULL COMMENT '작성자 이메일',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성자 정보 생성일',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '작성자 정보 수정일'
)COMMENT '일정 작성자 관리 테이블';

-- 스케쥴 테이블 생성
CREATE TABLE schedules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 관리 식별자',
    writer_id BIGINT NOT NULL COMMENT '작성자 식별자',
    password VARCHAR(100) NOT NULL COMMENT '게시글 비밀번호 (수정/삭제 시 필요)',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '게시글 생성일',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '게시글 수정일',
    contents TEXT NOT NULL COMMENT '할 일 내용',
    CONSTRAINT fk_writer_id FOREIGN KEY (writer_id) REFERENCES writers(id) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='일정 관리 테이블';
-- 작성자 데이터가 사라지면 작성자가 작성한 게시글들도 같이 삭제