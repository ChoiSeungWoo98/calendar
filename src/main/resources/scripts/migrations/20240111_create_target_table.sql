use calender;

DROP TABLE IF EXISTS tb_target;
CREATE TABLE tb_target
(
    no                      INT         AUTO_INCREMENT  NOT NULL                                COMMENT '고유 번호'   PRIMARY KEY,
    title                   VARCHAR(64)                 NOT NULL                                COMMENT '타이틀',
    type                    VARCHAR(1)                  NOT NULL                                COMMENT '타입 (Y : 연간 / M : 월간 / D : 일간)',
    year                    VARCHAR(4)                  NULL                                    COMMENT '노출연도',
    month                   VARCHAR(4)                  NULL                                    COMMENT '노출 월',
    day                     VARCHAR(4)                  NULL                                    COMMENT '노출 일',
    time                    VARCHAR(6)                  NULL                                    COMMENT '시간 - TODO만 해당',
    repeat_yn               VARCHAR(1)                  NULL                                    COMMENT '반복 여부(Y / N) - TODO만 해당',
    success_yn              VARCHAR(1)                  NOT NULL    DEFAULT 'N'                 COMMENT '성공 여부(Y / N)',
    delete_yn               VARCHAR(1)                  NOT NULL    DEFAULT 'N'                 COMMENT '삭제 여부 (Y / N)',
    reg_date                DATETIME                    NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '생성 일자'
) comment '목표 정보' charset = utf8mb3;

