package com.choi.calender.application.target.service;

import com.choi.calender.application.diary.dto.DiaryDto;
import com.choi.calender.application.target.dto.TargetDto;
import com.choi.calender.application.target.dto.TodoTargetDto;
import com.choi.calender.domain.api.SearchTargetBean;

import java.util.List;

public interface TargetService {

    String insertTarget(TargetDto targetDto);

    String updateSuccessYn(TargetDto targetDto);

    String insertTodoSuccessYn(TodoTargetDto todoTargetDto);

    List<TargetDto> selectTarget(SearchTargetBean searchTargetBean);

    List<TargetDto> selectRepeatTodoTarget(SearchTargetBean searchTargetBean);

}
