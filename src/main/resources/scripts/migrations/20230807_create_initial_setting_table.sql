use xe_management;

-- 유저 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_user;
CREATE TABLE tb_user
(
    id                      VARCHAR(64)     NOT NULL                                        COMMENT '아이디'   PRIMARY KEY,
    pw                      VARCHAR(64)     NOT NULL    DEFAULT 'iXVzPyufQ4gY37cUQCO1aw=='  COMMENT '비밀번호',
    name                    VARCHAR(16)     NOT NULL                                        COMMENT '이름',
    email                   VARCHAR(64)     NULL                                            COMMENT '이메일',
    phone                   VARCHAR(64)     NULL                                            COMMENT '핸드폰 번호',
    company_name            VARCHAR(32)     NULL                                            COMMENT '기업명',
    position                VARCHAR(32)     NULL                                            COMMENT '직책',
    auth                    VARCHAR(1)      NOT NULL                                        COMMENT '권한(관리자 : A, 사용자 : U)',
    etc                     TEXT            NULL                                            COMMENT '기타 정보',
    delete_yn               VARCHAR(1)      NOT NULL    DEFAULT 'N'                         COMMENT '삭제 여부(비활성화 여부) (삭제 : Y, 미삭제 : N)',
    approve_yn              VARCHAR(1)      NOT NULL    DEFAULT 'N'                         COMMENT '승인 여부 (승인 : Y, 미승인 : N)',
    reg_date                DATETIME        NOT NULL    DEFAULT CURRENT_TIMESTAMP           COMMENT '가입 일자',
    modify_date             DATETIME        NULL                                            COMMENT '수정 일자',
    delete_date             DATETIME        NULL                                            COMMENT '삭제 일자',
    last_connection_date    DATETIME        NULL                                            COMMENT '마지막 접속 일자'
) comment '유저 정보' charset = utf8mb3;

-- 그룹 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_group;
CREATE TABLE tb_group
(
    no              INT             NOT NULL    AUTO_INCREMENT              COMMENT '그룹 고유번호'   PRIMARY KEY,
    name            VARCHAR(32)     NOT NULL                                COMMENT '그룹 이름',
    content         TEXT            NOT NULL                                COMMENT '그룹 설명란',
    delete_yn       VARCHAR(1)      NOT NULL    DEFAULT 'N'                 COMMENT '삭제 여부(비활성화 여부) (삭제 : Y, 미삭제 : N)',
    reg_date        DATETIME        NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '그룹 생성 일자',
    modify_date     DATETIME        NULL                                    COMMENT '그룹 수정 일자',
    delete_date     DATETIME        NULL                                    COMMENT '그룹 삭제 일자'
) comment '그룹 정보' charset = utf8mb3;

-- 그룹 멤버 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_group_members;
CREATE TABLE tb_group_members
(
    no                  INT             NOT NULL    AUTO_INCREMENT              COMMENT '그룹 멤버 고유번호'   PRIMARY KEY,
    group_no            INT             NOT NULL                                COMMENT '그룹 번호',
    id                  VARCHAR(64)     NOT NULL                                COMMENT '유저 아이디',
    responsibility_yn   VARCHAR(1)      NOT NULL    DEFAULT 'N'                 COMMENT '책임자 여부(책임자 : Y, 참여자 : N)',
    FOREIGN KEY (group_no) REFERENCES tb_group(no) ON UPDATE CASCADE,
    FOREIGN KEY (id) REFERENCES tb_user(id) ON UPDATE CASCADE
) comment '그룹 정보' charset = utf8mb3;

-- 프로젝트 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_work;
CREATE TABLE tb_work
(
    no              INT             NOT NULL    AUTO_INCREMENT              COMMENT '프로젝트 고유번호' PRIMARY KEY,
    group_no        INT             NOT NULL                                COMMENT '프로젝트 참여 그룹 번호',
    company_name    VARCHAR(64)     NOT NULL                                COMMENT '프로젝트 참여 기업명',
    title           VARCHAR(64)     NOT NULL                                COMMENT '프로젝트 명',
    start_date      DATE            NOT NULL                                COMMENT '프로젝트 시작 일자',
    end_date        DATE            NOT NULL                                COMMENT '프로젝트 종료 일자',
    status          VARCHAR(1)      NOT NULL                                COMMENT '프로젝트 진행 상태(협의 : W, 진행중 : P, 완료 : S, 유지보수 : M)',
    content         TEXT            NOT NULL                                COMMENT '프로젝트 내용(파일 정보는 파일 테이블에서 조인)',
    delete_yn       VARCHAR(1)      NOT NULL    DEFAULT 'N'                 COMMENT '삭제 여부(비활성화 여부) (삭제 : Y, 미삭제 : N)',
    reg_date        DATETIME        NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '프로젝트 생성 일자',
    modify_date     DATETIME        NULL                                    COMMENT '프로젝트 수정 일자',
    delete_date     DATETIME        NULL                                    COMMENT '프로젝트 삭제 일자',
    FOREIGN KEY (group_no) REFERENCES tb_group(no) ON UPDATE CASCADE
) comment '프로젝트 정보' charset = utf8mb3;

-- 파일 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_file;
CREATE TABLE tb_file
(
    no              INT             NOT NULL    AUTO_INCREMENT              COMMENT '프로젝트 고유번호' PRIMARY KEY,
    key_no          INT             NOT NULL                                COMMENT '부모 키 번호',
    identifier      VARCHAR(64)     NOT NULL                                COMMENT '식별자',
    ori_file_name   VARCHAR(64)     NOT NULL                                COMMENT '원본 파일 명',
    file_name       VARCHAR(64)     NOT NULL                                COMMENT '저장된 파일 명',
    type            VARCHAR(32)     NOT NULL                                COMMENT '파일 변환 타입',
    size            VARCHAR(16)     NOT NULL                                COMMENT '파일 크기',
    reg_date        DATETIME        NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '프로젝트 생성 일자'
) comment '파일 정보' charset = utf8mb3;

-- 보드 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_board;
CREATE TABLE tb_board
(
    no              INT             NOT NULL    AUTO_INCREMENT              COMMENT '보드 고유번호'   PRIMARY KEY,
    id              VARCHAR(64)     NOT NULL                                COMMENT '작성한 유저 아이디',
    title           VARCHAR(64)     NOT NULL                                COMMENT '제목',
    category        VARCHAR(1)      NOT NULL                                COMMENT '보드 카테고리(협의 : D, 추가 개발 : A, 이슈 : I, 수정 : M, 보류 : W, 문서요청 : R)',
    status          VARCHAR(1)      NOT NULL    DEFAULT 'W'                 COMMENT '상태(미진행 : W, 진행중 : P, 완료 : S, 작업 아님 : N)',
    content         TEXT            NOT NULL                                COMMENT '보드 내용(파일 정보는 파일 테이블에서 조인)',
    view_cnt        INT             NOT NULL    DEFAULT 0                   COMMENT '조회수',
    answer_yn       VARCHAR(1)      NOT NULL    DEFAULT 'N'                 COMMENT '답변 여부(답변 완료 : Y, 답변 대기 : N)',
    delete_yn       VARCHAR(1)      NOT NULL    DEFAULT 'N'                 COMMENT '삭제 여부(비활성화 여부) (삭제 : Y, 미삭제 : N)',
    reg_date        DATETIME        NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '보드 생성 일자',
    modify_date     DATETIME        NULL                                    COMMENT '보드 수정 일자',
    delete_date     DATETIME        NULL                                    COMMENT '보드 삭제 일자',
    FOREIGN KEY (id) REFERENCES tb_user(id) ON UPDATE CASCADE
) comment '프로젝트 정보' charset = utf8mb3;

-- 댓글 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_comment;
CREATE TABLE tb_comment
(
    no              INT             NOT NULL    AUTO_INCREMENT              COMMENT '댓글 고유번호' PRIMARY KEY,
    id              VARCHAR(64)     NOT NULL                                COMMENT '작성한 유저 아이디',
    key_no          INT             NOT NULL                                COMMENT '부모 키 번호',
    identifier      VARCHAR(64)     NOT NULL                                COMMENT '식별자',
    content         VARCHAR(256)    NOT NULL                                COMMENT '댓글 내용',
    delete_yn       VARCHAR(1)      NOT NULL    DEFAULT 'N'                 COMMENT '삭제 여부(비활성화 여부) (삭제 : Y, 미삭제 : N)',
    reg_date        DATETIME        NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '댓글 생성 일자',
    modify_date     DATETIME        NULL                                    COMMENT '댓글 수정 일자',
    delete_date     DATETIME        NULL                                    COMMENT '댓글 삭제 일자',
    FOREIGN KEY (id) REFERENCES tb_user(id) ON UPDATE CASCADE
) comment '파일 정보' charset = utf8mb3;

-- 로그 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_log;
CREATE TABLE tb_log
(
    no              INT             NOT NULL    AUTO_INCREMENT              COMMENT '로그 고유번호'   PRIMARY KEY,
    id              VARCHAR(64)     NOT NULL                                COMMENT '유저 아이디',
    content         TEXT            NOT NULL                                COMMENT '로그 내용',
    reg_date        DATETIME        NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '로그 생성 일자',
    FOREIGN KEY (id) REFERENCES tb_user(id) ON UPDATE CASCADE
) comment '그룹 정보' charset = utf8mb3;

-- 사이트 탭 테이블
-- auto-generated definition
DROP TABLE IF EXISTS tb_tab;
CREATE TABLE tb_tab
(
    no              INT             NOT NULL    AUTO_INCREMENT              COMMENT '사이트탭 고유번호'   PRIMARY KEY,
    identifier      VARCHAR(64)     NOT NULL                                COMMENT '식별자',
    name            VARCHAR(16)     NOT NULL                                COMMENT '탭 이름',
    class_name        VARCHAR(64)     NOT NULL                                COMMENT '탭 클래스명(영문명)',
    url             VARCHAR(64)     NOT NULL                                COMMENT '이동 URL',
    reg_date        DATETIME        NOT NULL    DEFAULT CURRENT_TIMESTAMP   COMMENT '탭 생성 일자'
) comment '탭 정보' charset = utf8mb3;