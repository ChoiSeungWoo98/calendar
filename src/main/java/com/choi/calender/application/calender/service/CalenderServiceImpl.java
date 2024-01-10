package com.choi.calender.application.calender.service;

import com.choi.calender.application.calender.dto.CalenderDto;
import com.choi.calender.domain.api.CalenderBean;
import com.choi.calender.mapper.CalenderMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CalenderServiceImpl implements CalenderService {

    @Resource
    private CalenderMapper calenderMapper;

    @Override
    public String insertDiary(CalenderDto calenderDto) {
        CalenderBean calenderBean = new CalenderBean().convertBeanDto(calenderDto);
        return calenderMapper.insertDiary(calenderBean) == 1
                ? "오늘도 고생했지만 내일은 더 열심히 해보자!"
                : "저장에 실패했습니다. 코드를 수정해주세요.";
    }
}