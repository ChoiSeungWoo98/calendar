use calender;

-- 목표 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_target;
CREATE TABLE tb_target
(
    no                      INT         AUTO_INCREMENT  NOT NULL                                COMMENT '고유 번호'   PRIMARY KEY,
    title                   VARCHAR(64)                 NOT NULL                                COMMENT '타이틀',
    type                    VARCHAR(1)                  NOT NULL                                COMMENT '타입 (Y : 연간 / M : 월간 / D : 일간)',
    time                    VARCHAR(6)                  NULL                                    COMMENT '시간 - TODO만 해당',
    repeat_yn               VARCHAR(1)                  NULL                                    COMMENT '반복 여부(Y / N) - TODO만 해당',
    delete_yn               VARCHAR(1)                  NOT NULL    DEFAULT 'N'                 COMMENT '삭제 여부 (Y / N)',
    reg_date                DATETIME                    NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '생성 일자'
) comment '목표 정보' charset = utf8mb3;

-- 이벤트 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_event;
CREATE TABLE tb_event
(
    no                      INT         AUTO_INCREMENT  NOT NULL                                COMMENT '고유 번호'   PRIMARY KEY,
    title                   VARCHAR(64)                 NOT NULL                                COMMENT '타이틀',
    event_date              DATETIME                    NOT NULL                                COMMENT '이벤트 날짜',
    type                    VARCHAR(1)                  NOT NULL                                COMMENT '타입 (S : 양력, L : 음력)',
    holiday_yn              VARCHAR(1)                  NOT NULL                                COMMENT '공휴일 여부(Y / N)',
    repeat_yn               VARCHAR(1)                  NOT NULL                                COMMENT '반복 여부(Y / N)',
    delete_yn               VARCHAR(1)                  NOT NULL    DEFAULT 'N'                 COMMENT '삭제 여부 (Y / N)',
    reg_date                DATETIME                    NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '생성 일자'
) comment '이벤트 정보' charset = utf8mb3;

-- 파일 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_file;
CREATE TABLE tb_file
(
    no            INT           AUTO_INCREMENT                      COMMENT '프로젝트 고유번호'    PRIMARY KEY,
    key_no        INT                                   NOT NULL    COMMENT '부모 키 번호',
    identifier    VARCHAR(64)                           NOT NULL    COMMENT '식별자',
    ori_file_name VARCHAR(64)                           NOT NULL    COMMENT '원본 파일 명',
    file_name     VARCHAR(64)                           NOT NULL    COMMENT '저장된 파일 명',
    type          VARCHAR(32)                           NOT NULL    COMMENT '파일 변환 타입',
    size          VARCHAR(16)                           NOT NULL    COMMENT '파일 크기',
    reg_date      DATETIME DEFAULT CURRENT_TIMESTAMP    NOT NULL    COMMENT '프로젝트 생성 일자'
) comment '파일 정보' charset = utf8mb3;

-- 다이어리 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_diary;
CREATE TABLE tb_diary
(
    no                      INT         AUTO_INCREMENT  NOT NULL                                COMMENT '고유 번호'         PRIMARY KEY,
    diary_date              DATE                        NOT NULL                                COMMENT '다이어리 일자',
    weather                 VARCHAR(8)                  NOT NULL                                COMMENT '날씨',
    emotion                 VARCHAR(64)                 NULL                                    COMMENT '감정',
    content                 TEXT                        NULL                                    COMMENT '일기',
    delete_yn               VARCHAR(1)                  NOT NULL    DEFAULT 'N'                 COMMENT '삭제 여부 (Y / N)',
    reg_date                DATETIME                    NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '생성 일자',
    UNIQUE KEY date_key (diary_date)
) comment '다이어리 정보' charset = utf8mb3;

-- 다이어리 / 이벤트 매칭 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_event_by_day;
CREATE TABLE tb_event_by_day
(
    no                      INT         AUTO_INCREMENT  NOT NULL                                COMMENT '고유 번호'         PRIMARY KEY,
    dairy_no                INT                         NOT NULL                                COMMENT '다이어리 번호',
    event_no                INT                         NOT NULL                                COMMENT '이벤트 번호',
    success_yn              VARCHAR(1)                  NOT NULL    DEFAULT 'N'                 COMMENT '성공 여부 (Y / N)',
    delete_yn               VARCHAR(1)                  NOT NULL    DEFAULT 'N'                 COMMENT '삭제 여부 (Y / N)',
    reg_date                DATETIME                    NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '생성 일자',
    UNIQUE KEY date_key (dairy_no, event_no)
) comment '일자별 이벤트 정보' charset = utf8mb3;
