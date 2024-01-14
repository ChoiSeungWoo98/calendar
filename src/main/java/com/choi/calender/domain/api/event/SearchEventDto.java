package com.choi.calender.domain.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchEventDto {
    protected String eventDate;
    protected String month;
    protected String type;
    protected String holidayYn;
    protected String repeatYn;

    public SearchEventDto(
            String eventDate,
            String month,
            String type,
            String holidayYn,
            String repeatYn
    ) {
        this.eventDate = eventDate;
        this.month = month;
        this.type = type;
        this.holidayYn = holidayYn;
        this.repeatYn = repeatYn;
    }

}