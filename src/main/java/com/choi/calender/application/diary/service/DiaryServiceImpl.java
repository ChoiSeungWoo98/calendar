package com.choi.calender.application.diary.service;

import com.choi.calender.application.diary.dto.DiaryDto;
import com.choi.calender.domain.api.DiaryBean;
import com.choi.calender.mapper.DiaryMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Resource
    private DiaryMapper diaryMapper;

    @Override
    public String insertDiary(DiaryDto diaryDto) {
        DiaryBean diaryBean = new DiaryBean().convertBeanDto(diaryDto);
        return diaryMapper.insertDiary(diaryBean) == 1
                ? "오늘도 고생했지만 내일은 더 열심히 해보자!"
                : "저장에 실패했습니다. 코드를 수정해주세요.";
    }
}