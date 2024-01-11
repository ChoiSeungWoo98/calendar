package com.choi.calender.application.calender.service;

import com.choi.calender.application.calender.dto.DiaryDto;
import com.choi.calender.application.calender.dto.TargetDto;
import com.choi.calender.domain.api.SearchTargetBean;
import com.choi.calender.domain.api.TargetBean;

import java.util.List;

public interface CalenderService {

    String insertDiary(DiaryDto diaryDto);

    String insertTarget(TargetDto targetDto);

    List<TargetDto> selectYearTarget(SearchTargetBean searchTargetBean);

    List<TargetDto> selectMonthTarget(SearchTargetBean searchTargetBean);

}
