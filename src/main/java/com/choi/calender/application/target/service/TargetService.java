package com.choi.calender.application.target.service;

import com.choi.calender.application.diary.dto.DiaryDto;
import com.choi.calender.application.target.dto.TargetDto;
import com.choi.calender.domain.api.SearchTargetBean;

import java.util.List;

public interface TargetService {

    String insertTarget(TargetDto targetDto);

    List<TargetDto> selectYearTarget(SearchTargetBean searchTargetBean);

    List<TargetDto> selectMonthTarget(SearchTargetBean searchTargetBean);

}
