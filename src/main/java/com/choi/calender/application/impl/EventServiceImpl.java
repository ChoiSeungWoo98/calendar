package com.choi.calender.application.impl;

import com.choi.calender.application.dto.event.EventDto;
import com.choi.calender.application.service.EventService;
import com.choi.calender.domain.api.event.NationalHolidayBean;
import com.choi.calender.domain.api.event.SearchEventDto;
import com.choi.calender.mapper.EventMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Resource
    private EventMapper eventMapper;

    @Override
    public boolean isYearEvent(String year) {
        return eventMapper.isYearEvent(year);
    }

    @Override
    public List<EventDto> selectEventList(SearchEventDto searchEventDto) {
        return eventMapper.selectEventList(searchEventDto).stream().map(event -> new EventDto().convertBeanToDto(event)).collect(Collectors.toList());
    }

    @Override
    public boolean insertEvents(List<NationalHolidayBean> list) throws IOException {
        return eventMapper.insertEvents(list) >= 1
                ? true
                : false;
    }

}