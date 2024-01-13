package com.choi.calender.application.service;

import com.choi.calender.application.dto.TargetDto;
import com.choi.calender.application.dto.TodoTargetDto;
import com.choi.calender.domain.api.SearchTargetBean;

import java.util.List;

public interface TargetService {

    String insertTarget(TargetDto targetDto);

    String updateSuccessYn(TargetDto targetDto);

    String insertTodoSuccessYn(TodoTargetDto todoTargetDto);

    List<TargetDto> selectTarget(SearchTargetBean searchTargetBean);

    List<TargetDto> selectRepeatTodoTarget(SearchTargetBean searchTargetBean);

}
