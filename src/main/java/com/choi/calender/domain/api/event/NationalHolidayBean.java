package com.choi.calender.domain.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Getter
@NoArgsConstructor
public class NationalHolidayBean {
    protected int no;
    protected String title;
    protected Date eventDate;
    protected String type;
    protected String holidayYn;
    protected String repeatYn;
    protected String deleteYn;
    protected Date regDate;

    public NationalHolidayBean(
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

    public NationalHolidayBean(
            String title,
            Date eventDate,
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
        String changeTitle = changeTitle((String) data.get("dateName"));
        Date paseDate = null;

        try {
            paseDate = new SimpleDateFormat("yyyyMMdd").parse(String.valueOf(data.get("locdate")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new NationalHolidayBean(
            changeTitle,
            paseDate,
            "S",
            (String) data.get("isHoliday"),
            "N"
        );
    }

    public String changeTitle(String title) {
        if("1월1일".equals(title)) {
            return "신년";
        }
        if("기독탄신일".equals(title)) {
            return "크리스마스";
        }
        return title;
    }

}