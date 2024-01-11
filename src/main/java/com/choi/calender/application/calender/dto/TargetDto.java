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
public class TargetDto {
    protected String title;
    protected String type;
    protected String year;
    protected String time;
    protected String repeatYn;
    protected String successYn;

    public TargetDto(
            String title,
            String type,
            String year,
            String time,
            String repeatYn,
            String successYn
    ) {
        this.title = title;
        this.type = type;
        this.year = year;
        this.time = time;
        this.repeatYn = repeatYn;
        this.successYn = successYn;
    }

    public boolean isDataEmptyCheck() {
        return
            StringUtils.isBlank(this.title)
            || StringUtils.isBlank(this.type)
            || StringUtils.isBlank(this.year)
        ;
    }

    public boolean isAllDataEmptyCheck() {
        return
            StringUtils.isBlank(this.title)
            || StringUtils.isBlank(this.type)
            || StringUtils.isBlank(this.year)
            || StringUtils.isBlank(this.time)
            || StringUtils.isBlank(this.repeatYn)
            || StringUtils.isBlank(this.successYn)
        ;
    }
}
