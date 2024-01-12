package com.choi.calender.domain.api;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchTargetBean {
    protected String type;
    protected String year;
    protected String month;
    protected String day;
    protected int diaryNo;

    public SearchTargetBean(
            String type,
            String year,
            String month,
            String day,
            int diaryNo
    ) {
        this.type = type;
        this.year = year;
        this.month = month;
        this.day = day;
        this.diaryNo = diaryNo;
    }

    public SearchTargetBean(
            String type,
            String year,
            String month
    ) {
        this.type = type;
        this.year = year;
        this.month = month;
    }

}