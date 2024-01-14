package com.choi.calender.domain.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

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

}