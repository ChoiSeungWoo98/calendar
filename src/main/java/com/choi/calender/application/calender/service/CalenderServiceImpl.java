package com.choi.calender.application.calender.service;

import com.choi.calender.application.calender.dto.DiaryDto;
import com.choi.calender.application.calender.dto.TargetDto;
import com.choi.calender.domain.api.DiaryBean;
import com.choi.calender.domain.api.SearchTargetBean;
import com.choi.calender.domain.api.TargetBean;
import com.choi.calender.mapper.CalenderMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalenderServiceImpl implements CalenderService {

    @Resource
    private CalenderMapper calenderMapper;

    @Override
    public String insertDiary(DiaryDto diaryDto) {
        DiaryBean diaryBean = new DiaryBean().convertBeanDto(diaryDto);
        return calenderMapper.insertDiary(diaryBean) == 1
                ? "오늘도 고생했지만 내일은 더 열심히 해보자!"
                : "저장에 실패했습니다. 코드를 수정해주세요.";
    }

    @Override
    public String insertTarget(TargetDto targetDto) {
        TargetBean targetBean = new TargetBean().convertBeanDto(targetDto);
        return calenderMapper.insertTarget(targetBean) == 1
                ? "새로운 목표를 향해 화이팅!"
                : "저장에 실패했습니다. 코드를 수정해주세요.";
    }

    @Override
    public List<TargetDto> selectYearTarget(SearchTargetBean searchTargetBean) {
        List<TargetBean> targetBeans = calenderMapper.selectYearTarget(searchTargetBean);
        return null;
    }

    @Override
    public List<TargetDto> selectMonthTarget(SearchTargetBean searchTargetBean) {
        List<TargetBean> targetBeans = calenderMapper.selectMonthTarget(searchTargetBean);
        return null;
    }
}