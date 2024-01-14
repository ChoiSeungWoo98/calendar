package com.choi.calender.controller.api;

import com.choi.calender.application.dto.event.EventDto;
import com.choi.calender.application.service.EventService;
import com.choi.calender.domain.api.event.SearchEventDto;
import com.choi.calender.domain.value.ReturnStatus;
import com.choi.calender.util.ReturnMessage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calender/event")
@Slf4j
public class EventController {

    @Resource
    EventService eventService;

    @GetMapping("/find/month")
    public ReturnMessage findMonthEvent(
            @ModelAttribute("searchEventDto") SearchEventDto searchEventDto
    ) {
        if(StringUtils.isBlank(searchEventDto.getEventDate())) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }

        try {
            return new ReturnMessage(eventService.selectEventList(searchEventDto));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "일기 저장 실패", e);
        }
    }

    @PostMapping("/add")
    public ReturnMessage addEvent(
            @ModelAttribute("eventDto") EventDto eventDto
    ) {
        if(eventDto.isDataEmptyCheck()) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }

        try {
            return new ReturnMessage(eventService.addEvent(eventDto));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "일기 저장 실패", e);
        }
    }
}
