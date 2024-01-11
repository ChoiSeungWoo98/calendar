package com.choi.calender.mapper;

import com.choi.calender.domain.api.DiaryBean;
import com.choi.calender.domain.api.TargetBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalenderMapper {

    int insertDiary(DiaryBean diaryBean);

    int insertTarget(TargetBean targetBean);

}