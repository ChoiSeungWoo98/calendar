create table if not exists calender.tb_diary
(
    no          int auto_increment comment '고유 번호'
    primary key,
    diary_date  date                                 not null comment '다이어리 작성 일자',
    day_of_week varchar(8)                           not null comment '다이어리 작성 요일',
    weather     varchar(8)                           not null comment '날씨',
    temp        varchar(8)                           not null comment '온도',
    emotion     varchar(128)                         null comment '감정',
    content     text                                 null comment '일기',
    delete_yn   varchar(1) default 'N'               not null comment '삭제 여부 (Y / N)',
    reg_date    datetime   default CURRENT_TIMESTAMP not null comment '생성 일자',
    constraint date_key
    unique (diary_date)
    )
    comment '다이어리 정보' charset = utf8mb3;

create table if not exists calender.tb_event
(
    no         int auto_increment comment '고유 번호'
    primary key,
    title      varchar(64)                          not null comment '타이틀',
    event_date datetime                             not null comment '이벤트 날짜',
    type       varchar(1)                           not null comment '타입 (S : 양력, L : 음력)',
    holiday_yn varchar(1)                           not null comment '공휴일 여부(Y / N)',
    repeat_yn  varchar(1)                           not null comment '반복 여부(Y / N)',
    delete_yn  varchar(1) default 'N'               not null comment '삭제 여부 (Y / N)',
    reg_date   datetime   default CURRENT_TIMESTAMP not null comment '생성 일자'
    )
    comment '이벤트 정보' charset = utf8mb3;

create table if not exists calender.tb_event_by_day
(
    no         int auto_increment comment '고유 번호'
    primary key,
    dairy_no   int                                  not null comment '다이어리 번호',
    event_no   int                                  not null comment '이벤트 번호',
    success_yn varchar(1) default 'N'               not null comment '성공 여부 (Y / N)',
    delete_yn  varchar(1) default 'N'               not null comment '삭제 여부 (Y / N)',
    reg_date   datetime   default CURRENT_TIMESTAMP not null comment '생성 일자',
    constraint date_key
    unique (dairy_no, event_no)
    )
    comment '일자별 이벤트 정보' charset = utf8mb3;

create table if not exists calender.tb_file
(
    no            int auto_increment comment '프로젝트 고유번호'
    primary key,
    key_no        int                                not null comment '부모 키 번호',
    identifier    varchar(64)                        not null comment '식별자',
    ori_file_name varchar(64)                        not null comment '원본 파일 명',
    file_name     varchar(64)                        not null comment '저장된 파일 명',
    ext           varchar(32)                        not null comment '파일 타입',
    size          varchar(16)                        not null comment '파일 크기',
    reg_date      datetime default CURRENT_TIMESTAMP not null comment '프로젝트 생성 일자'
    )
    comment '파일 정보' charset = utf8mb3;

create table if not exists calender.tb_lunar
(
    no         int auto_increment comment '고유 번호'
    primary key,
    event_no   int                                  not null comment '이벤트 번호',
    lunar_date datetime                             not null comment '음력 달력',
    delete_yn  varchar(1) default 'N'               not null comment '삭제 여부 (Y / N)',
    reg_date   datetime   default CURRENT_TIMESTAMP not null comment '생성 일자'
    )
    comment '음력 이벤트 변환 정보' charset = utf8mb3;

create table if not exists calender.tb_target
(
    no         int auto_increment comment '고유 번호'
    primary key,
    title      varchar(128)                         not null comment '타이틀',
    type       varchar(1)                           not null comment '타입 (Y : 연간 / M : 월간 / D : 일간)',
    year       varchar(4)                           null comment '노출연도',
    month      varchar(4)                           null comment '노출 월',
    day        varchar(4)                           null comment '노출 일',
    time       varchar(6)                           null comment '시간 - TODO만 해당',
    repeat_yn  varchar(1)                           null comment '반복 여부(Y / N) - TODO만 해당',
    success_yn varchar(1) default 'N'               not null comment '성공 여부(Y / N)',
    delete_yn  varchar(1) default 'N'               not null comment '삭제 여부 (Y / N)',
    reg_date   datetime   default CURRENT_TIMESTAMP not null comment '생성 일자'
    )
    comment '목표 정보' charset = utf8mb3;

create table if not exists calender.tb_target_todo_success
(
    no         int auto_increment comment '고유 번호'
    primary key,
    diary_no   int                                  not null comment '다이어리 번호',
    target_no  int                                  not null comment '타겟 번호',
    success_yn varchar(1) default 'N'               not null comment '성공 여부(Y / N)',
    delete_yn  varchar(1) default 'N'               not null comment '삭제 여부 (Y / N)',
    reg_date   datetime   default CURRENT_TIMESTAMP not null comment '생성 일자',
    constraint date_key
    unique (diary_no, target_no)
    )
    comment '일일 목표 성공 여부 테이블' charset = utf8mb3;
