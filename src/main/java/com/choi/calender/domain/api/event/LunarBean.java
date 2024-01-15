package com.choi.calender.domain.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Getter
@NoArgsConstructor
public class LunarBean {
    protected int no;
    protected String lunYear;
    protected String lunMonth;
    protected String lunDay;
    protected String solYear;
    protected String solMonth;
    protected String solDay;

    public LunarBean(
        int no,
        String lunYear,
        String lunMonth,
        String lunDay,
        String solYear,
        String solMonth,
        String solDay
    ) {
        this.no = no;
        this.lunYear = lunYear;
        this.lunMonth = lunMonth;
        this.lunDay = lunDay;
        this.solYear = solYear;
        this.solMonth = solMonth;
        this.solDay = solDay;
    }

    public LunarBean convertMapToBean(Map data, int no) {
        return new LunarBean(
            no,
            String.valueOf(data.get("lunYear")),
            String.valueOf(data.get("lunMonth")),
            String.valueOf(data.get("lunDay")),
            String.valueOf(data.get("solYear")),
            String.valueOf(data.get("solMonth")),
            String.valueOf(data.get("solDay"))
        );
    }
}