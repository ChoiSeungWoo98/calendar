package com.choi.calender.mapper;

import com.choi.calender.application.calender.dto.TargetDto;
import com.choi.calender.domain.api.DiaryBean;
import com.choi.calender.domain.api.SearchTargetBean;
import com.choi.calender.domain.api.TargetBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalenderMapper {

    int insertDiary(DiaryBean diaryBean);

    int insertTarget(TargetBean targetBean);

    List<TargetBean> selectYearTarget(SearchTargetBean searchTargetBean);

    List<TargetBean> selectMonthTarget(SearchTargetBean searchTargetBean);

}