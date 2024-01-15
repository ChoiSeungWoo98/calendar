package com.choi.calender.application.impl;

import com.choi.calender.application.dto.event.EventDto;
import com.choi.calender.application.service.EventService;
import com.choi.calender.domain.api.event.EventBean;
import com.choi.calender.domain.api.event.LunarBean;
import com.choi.calender.domain.api.event.NationalHolidayBean;
import com.choi.calender.domain.api.event.SearchEventDto;
import com.choi.calender.mapper.EventMapper;
import com.choi.calender.util.ReturnMessage;
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
    public String addEvent(EventDto eventDto) {
        EventBean eventBean = new EventBean().convertDtoToBean(eventDto);
        if("S".equals(eventDto.getType())) {
            return eventMapper.addEvent(eventBean) > 0
                    ? "이벤트를 성공적으로 등록했어요!"
                    : "실패했어요! 코드를 수정해주세요.";
        }

        return String.valueOf(eventMapper.addEvent(eventBean));
    }

    @Override
    public boolean insertEvents(List<NationalHolidayBean> list) throws IOException {
        return eventMapper.insertEvents(list) >= 1
                ? true
                : false;
    }

    @Override
    public boolean insertLunarEvent(LunarBean lunarBean) throws IOException {
        return eventMapper.insertLunarEvent(lunarBean) >= 1
                ? true
                : false;
    }

}