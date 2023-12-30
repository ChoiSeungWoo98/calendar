package com.choi.calender.application.Impl;

import com.choi.calender.application.dto.calender.CalenderDto;
import com.choi.calender.application.service.CalenderService;
import com.choi.calender.domain.api.CalenderBean;
import com.choi.calender.mapper.CalenderMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CalenderServiceImpl implements CalenderService {

    @Resource
    private CalenderMapper calenderMapper;

    @Override
    public String insertCalenderData(CalenderDto calenderDto) {
        CalenderBean calenderBean = new CalenderBean().convertBeanDto(calenderDto);
        return calenderMapper.insertCalenderData(calenderBean) == 1
                ? "아이디 : " + calenderBean.getTest6() + ", 비밀번호 : 0000"
                : "실패하였습니다.";
    }
}