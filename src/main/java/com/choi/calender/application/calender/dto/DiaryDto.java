package com.choi.calender.application.calender.dto;


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
    protected List<String> emotions;
    protected String content;

    public DiaryDto(
            String date,
            String dayOfWeek,
            String weather,
            List<String> emotions,
            String content
    ) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.weather = weather;
        this.emotions = emotions;
        this.content = content;
    }

    public boolean isDataEmptyCheck(DiaryDto diaryDto) {
        return
            StringUtils.isBlank(diaryDto.getDate())
            || StringUtils.isBlank(diaryDto.getDayOfWeek())
            || StringUtils.isBlank(diaryDto.getWeather())
            || ObjectUtils.isEmpty(diaryDto.getEmotions())
            || StringUtils.isBlank(diaryDto.getContent())
        ;
    }
}
