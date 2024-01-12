use calender;

DROP TABLE IF EXISTS tb_target_todo_success;
CREATE TABLE tb_target_todo_success
(
    no                      INT         AUTO_INCREMENT  NOT NULL                                COMMENT '고유 번호'   PRIMARY KEY,
    diary_no                INT                         NOT NULL                                COMMENT '다이어리 번호',
    target_no               INT                         NOT NULL                                COMMENT '타겟 번호',
    success_yn              VARCHAR(1)                  NOT NULL    DEFAULT 'N'                 COMMENT '성공 여부(Y / N)',
    delete_yn               VARCHAR(1)                  NOT NULL    DEFAULT 'N'                 COMMENT '삭제 여부 (Y / N)',
    reg_date                DATETIME                    NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '생성 일자',
    UNIQUE KEY date_key (diary_no, target_no)
) comment '일일 목표 성공 여부 테이블' charset = utf8mb3;

