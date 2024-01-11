package com.choi.calender.application.calender.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public class TargetDto {
    protected String title;
    protected String type;
    protected String year;
    protected String month;
    protected String day;
    protected String time;
    protected String repeatYn;
    protected String successYn;
    protected String deleteYn;

    public TargetDto(
            String title,
            String type,
            String year,
            String month,
            String day,
            String time,
            String repeatYn,
            String successYn,
            String deleteYn
    ) {
        this.title = title;
        this.type = type;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.repeatYn = repeatYn;
        this.successYn = successYn;
        this.deleteYn = deleteYn;
    }

    public boolean isYearDataEmptyCheck() {
        return
            StringUtils.isBlank(this.title)
            || StringUtils.isBlank(this.type)
            || StringUtils.isBlank(this.year)
        ;
    }

    public boolean isMonthDataEmptyCheck() {
        return
            StringUtils.isBlank(this.title)
            || StringUtils.isBlank(this.type)
            || StringUtils.isBlank(this.year)
            || StringUtils.isBlank(this.month)
        ;
    }

    public boolean isDayDataEmptyCheck() {
        return
            StringUtils.isBlank(this.title)
            || StringUtils.isBlank(this.type)
            || StringUtils.isBlank(this.year)
            || StringUtils.isBlank(this.month)
            || StringUtils.isBlank(this.day)
            || StringUtils.isBlank(this.time)
            || StringUtils.isBlank(this.repeatYn)
        ;
    }
}
