package com.choi.calender.application.dto.event;


import com.choi.calender.domain.api.file.FileBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {
    protected int no;
    protected String title;
    protected Instant event_date;
    protected String type;
    protected String holiday_yn;
    protected String repeat_yn;
    protected String delete_yn;
    protected Instant reg_date;

    public EventDto(
        int no,
        String title,
        Instant event_date,
        String type,
        String holiday_yn,
        String repeat_yn,
        String delete_yn,
        Instant reg_date
    ) {
        this.no = no;
        this.title = title;
        this.event_date = event_date;
        this.type = type;
        this.holiday_yn = holiday_yn;
        this.repeat_yn = repeat_yn;
        this.delete_yn = delete_yn;
        this.reg_date = reg_date;
    }

}
