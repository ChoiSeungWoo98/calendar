package com.choi.calender.application.service;

import com.choi.calender.application.dto.event.EventDto;
import com.choi.calender.domain.api.event.NationalHolidayBean;
import com.choi.calender.domain.api.event.SearchEventDto;

import java.io.IOException;
import java.util.List;

public interface EventService {

    boolean isYearEvent(String year);

    List<EventDto> selectEventList(SearchEventDto searchEventDto);

    String addEvent(EventDto eventDto);

    boolean insertEvents(List<NationalHolidayBean> list) throws IOException;

}
