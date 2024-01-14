package com.choi.calender.domain.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Getter
@NoArgsConstructor
public class NationalHolidayBean {
    protected int no;
    protected String title;
    protected Instant eventDate;
    protected String type;
    protected String holidayYn;
    protected String repeatYn;
    protected String deleteYn;
    protected Instant regDate;

    public NationalHolidayBean(
        int no,
        String title,
        Instant eventDate,
        String type,
        String holidayYn,
        String repeatYn,
        String deleteYn,
        Instant regDate
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

    public NationalHolidayBean(
            String title,
            Instant eventDate,
            String type,
            String holidayYn,
            String repeatYn
    ) {
        this.title = title;
        this.eventDate = eventDate;
        this.type = type;
        this.holidayYn = holidayYn;
        this.repeatYn = repeatYn;
    }

    public NationalHolidayBean convertMapToBean(Map data) {
        String tempTitle = (String) data.get("dateName");
        tempTitle = "1월1일".equals(tempTitle) ? "신년" : tempTitle;

        return new NationalHolidayBean(
            tempTitle,
            LocalDate.parse(String.valueOf(data.get("locdate")), DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay(ZoneId.systemDefault()).toInstant(),
            "S",
            (String) data.get("isHoliday"),
            "N"
        );
    }

}