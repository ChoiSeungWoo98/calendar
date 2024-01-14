package com.choi.calender.domain.api.event;

import com.choi.calender.application.dto.event.EventDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;


@Getter
@NoArgsConstructor
public class EventBean {
    protected int no;
    protected String title;
    protected Date eventDate;
    protected String type;
    protected String holidayYn;
    protected String repeatYn;
    protected String deleteYn;
    protected Date regDate;

    public EventBean(
        int no,
        String title,
        Date eventDate,
        String type,
        String holidayYn,
        String repeatYn,
        String deleteYn,
        Date regDate
    ) {
        this.no = no;
        this.title = title;
        this.eventDate = eventDate;
        this.type = type;
        this.holidayYn = holidayYn;
        this.repeatYn = repeatYn;
        this.deleteYn = deleteYn;
        this.regDate = regDate;
    }

    public EventBean convertDtoToBean(EventDto eventDto) {
        return new EventBean(
            eventDto.getNo(),
            eventDto.getTitle(),
            eventDto.getEventDate(),
            eventDto.getType(),
            StringUtils.isBlank(eventDto.getHolidayYn()) ? "N" : eventDto.getHolidayYn(),
            eventDto.getRepeatYn(),
            eventDto.getDeleteYn(),
            eventDto.getRegDate()
        );
    }

}