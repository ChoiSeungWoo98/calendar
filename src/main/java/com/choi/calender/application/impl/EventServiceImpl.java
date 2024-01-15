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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
    public List<EventDto> selectLunarList() {
        return eventMapper.selectLunarList().stream().map(event -> new EventDto().convertBeanToDto(event)).collect(Collectors.toList());
    }

    @Override
    public List<EventDto> selectEventList(SearchEventDto searchEventDto) {
        List<EventDto> tempList = eventMapper.selectEventList(searchEventDto).stream().map(event -> new EventDto().convertBeanToDto(event)).collect(Collectors.toList());
        List<EventDto> resultList = new ArrayList<>();
        boolean last = false;
        int j = 1;
        for(int i = 0; i < tempList.size() - 1; i++) {
            EventDto nowEventDto = tempList.get(i);
            EventDto frontEventDto = tempList.get(j);
            j++;
            int nowDay = nowEventDto.getEventDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth();
            int frontDay = frontEventDto.getEventDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth();
            if (nowDay != frontDay) {
                resultList.add(nowEventDto);
                if(j != tempList.size()) {
                    continue;
                }
                if(j == tempList.size()) {
                    last = true;
                }
            }
            if(last) {
                resultList.add(frontEventDto);
                continue;
            }
            EventDto eventDto = new EventDto();
            eventDto.setEventDate(nowEventDto.getEventDate());
            eventDto.setTitle(nowEventDto.getTitle() + ", " + frontEventDto.getTitle());
            eventDto.setHolidayYn(nowEventDto.getHolidayYn() + frontEventDto.getHolidayYn());
            resultList.add(eventDto);
        }
        return resultList;
    }

    @Override
    public String addEvent(EventDto eventDto) {
        if("S".equals(eventDto.getType())) {
            return eventMapper.addEvent(eventDto) > 0
                    ? "이벤트를 성공적으로 등록했어요!"
                    : "실패했어요! 코드를 수정해주세요.";
        }

        return String.valueOf(eventMapper.addEvent(eventDto));
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