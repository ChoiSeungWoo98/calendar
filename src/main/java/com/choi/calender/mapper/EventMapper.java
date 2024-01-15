package com.choi.calender.mapper;

import com.choi.calender.application.dto.event.EventDto;
import com.choi.calender.domain.api.event.EventBean;
import com.choi.calender.domain.api.event.LunarBean;
import com.choi.calender.domain.api.event.NationalHolidayBean;
import com.choi.calender.domain.api.event.SearchEventDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {

    boolean isYearEvent(String year);

    List<EventBean> selectLunarList();

    List<EventBean> selectEventList(SearchEventDto searchEventDto);

    int addEvent(EventDto eventDto);

    int insertEvents(List<NationalHolidayBean> list);

    int insertLunarEvent(LunarBean lunarBean);

}