package com.choi.calender.mapper;

import com.choi.calender.domain.api.event.NationalHolidayBean;
import com.choi.calender.domain.api.file.FileBean;
import com.choi.calender.domain.api.file.SearchFileBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {

    boolean isYearEvent(String year);

    List<FileBean> selectEventList(SearchFileBean searchFileBean);

    int insertEvents(List<NationalHolidayBean> list);

}