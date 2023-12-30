use xe_management;

-- 탭 테이블 sort(정렬) 추가
ALTER TABLE tb_tab ADD sort INT NOT NULL COMMENT '정렬순서' AFTER url;