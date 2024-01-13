package com.choi.calender.application.impl;

import com.choi.calender.application.dto.DiaryDto;
import com.choi.calender.application.service.DiaryService;
import com.choi.calender.domain.api.DiaryBean;
import com.choi.calender.mapper.DiaryMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Resource
    private DiaryMapper diaryMapper;

    @Override
    public String insertDiary(DiaryDto diaryDto) {
        DiaryBean diaryBean = new DiaryBean().convertDtoToBean(diaryDto);
        return diaryMapper.insertDiary(diaryBean) == 1
                ? "오늘도 고생했지만 내일은 더 열심히 해보자!"
                : "저장에 실패했습니다. 코드를 수정해주세요.";
    }

    @Override
    public String updateDiary(DiaryDto diaryDto) {
        DiaryBean diaryBean = new DiaryBean().convertDtoToBean(diaryDto);
        return diaryMapper.updateDiary(diaryBean) == 1
                ? "수정했어요! 오늘도 고생했지만 내일은 더 열심히 해보자!"
                : "저장에 실패했습니다. 코드를 수정해주세요.";
    }

    @Override
    public List<DiaryDto> selectThisMonthDiaryList(String diaryDate) {
        List<DiaryBean> diaryBeanList = diaryMapper.selectThisMonthDiaryList(diaryDate);
        if (diaryBeanList == null) {
            return null;
        }
        return diaryBeanList.stream().map(diaryBean -> new DiaryDto().convertBeanToDto(diaryBean)).collect(Collectors.toList());
    }

    @Override
    public DiaryDto selectDiary(String diaryDate) {
        DiaryBean diaryBean = diaryMapper.selectDiary(diaryDate);
        if (diaryBean == null) {
            return null;
        }
        return new DiaryDto().convertBeanToDto(diaryBean);
    }
}