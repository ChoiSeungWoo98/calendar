use xe_management;

-- 탭 테이블 url 필수 제거
ALTER TABLE tb_tab MODIFY url VARCHAR(64) NULL COMMENT '이동 URL';