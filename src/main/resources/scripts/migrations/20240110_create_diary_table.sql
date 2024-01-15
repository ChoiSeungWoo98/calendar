use calender;

DROP TABLE IF EXISTS tb_diary;
CREATE TABLE tb_diary
(
    no                      INT         AUTO_INCREMENT  NOT NULL                                COMMENT '고유 번호'         PRIMARY KEY,
    diary_date              DATE                        NOT NULL                                COMMENT '다이어리 작성 일자',
    day_of_week             VARCHAR(8)                  NOT NULL                                COMMENT '다이어리 작성 요일',
    weather                 VARCHAR(8)                  NOT NULL                                COMMENT '날씨',
    temp                    VARCHAR(8)                  NOT NULL                                COMMENT '온도',
    emotion                 VARCHAR(128)                NULL                                    COMMENT '감정',
    content                 TEXT                        NULL                                    COMMENT '일기',
    delete_yn               VARCHAR(1)                  NOT NULL    DEFAULT 'N'                 COMMENT '삭제 여부 (Y / N)',
    reg_date                DATETIME                    NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '생성 일자',
    UNIQUE KEY date_key (diary_date)
) comment '다이어리 정보' charset = utf8mb3;
