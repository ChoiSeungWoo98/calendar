package com.choi.calender.mapper;

import com.choi.calender.domain.api.event.EventBean;
import com.choi.calender.domain.api.event.NationalHolidayBean;
import com.choi.calender.domain.api.event.SearchEventDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {

    boolean isYearEvent(String year);

    List<EventBean> selectEventList(SearchEventDto searchEventDto);

    int insertEvents(List<NationalHolidayBean> list);

}