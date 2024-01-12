package com.choi.calender.application.diary.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DiaryDto {
    protected String date;
    protected String dayOfWeek;
    protected String weather;
    protected String temp;
    protected List<String> emotions;
    protected String content;

    public DiaryDto(
            String date,
            String dayOfWeek,
            String weather,
            String temp,
            List<String> emotions,
            String content
    ) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.weather = weather;
        this.temp = temp;
        this.emotions = emotions;
        this.content = content;
    }

    public boolean isDataEmptyCheck() {
        return
            StringUtils.isBlank(this.date)
            || StringUtils.isBlank(this.dayOfWeek)
            || StringUtils.isBlank(this.weather)
            || StringUtils.isBlank(this.temp)
            || ObjectUtils.isEmpty(this.emotions)
            || StringUtils.isBlank(this.content)
        ;
    }
}
