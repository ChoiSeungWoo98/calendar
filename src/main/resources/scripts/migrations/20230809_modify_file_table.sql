use xe_management;

-- 탭 테이블 url 필수 제거
alter table tb_file modify no int auto_increment comment '파일 고유번호';

alter table tb_file drop column identifier;

alter table tb_file modify size int not null comment '파일 크기';

alter table tb_file change type ext varchar(32) not null comment '파일 변환 타입';

