package com.choi.calender.domain.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class EventBean {
    protected int seq;
    protected String dateKind;
    protected String dateName;
    protected String isHoliday;
    protected Instant locdate;

    public EventBean(
            int seq,
            String dateKind,
            String dateName,
            String isHoliday,
            Instant locdate
    ) {
        this.seq = seq;
        this.dateKind = dateKind;
        this.dateName = dateName;
        this.isHoliday = isHoliday;
        this.locdate = locdate;
    }

}