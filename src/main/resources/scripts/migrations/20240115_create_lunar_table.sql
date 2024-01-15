use calender;

DROP TABLE IF EXISTS tb_lunar;
CREATE TABLE tb_lunar
(
    no                      INT         AUTO_INCREMENT  NOT NULL                                COMMENT '고유 번호'   PRIMARY KEY,
    event_no                INT                         NOT NULL                                COMMENT '이벤트 번호',
    lunar_date              DATETIME                    NOT NULL                                COMMENT '음력 달력',
    delete_yn               VARCHAR(1)                  NOT NULL    DEFAULT 'N'                 COMMENT '삭제 여부 (Y / N)',
    reg_date                DATETIME                    NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '생성 일자'
) COMMENT '음력 이벤트 변환 정보' CHARSET = utf8mb3;

