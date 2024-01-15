use calender;

ALTER TABLE tb_file CHANGE type ext VARCHAR(32) NOT NULL COMMENT '파일 타입';
