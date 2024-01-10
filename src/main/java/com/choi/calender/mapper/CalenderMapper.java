package com.choi.calender.mapper;

import com.choi.calender.domain.api.CalenderBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalenderMapper {

    int insertDiary(CalenderBean calenderBean);

}