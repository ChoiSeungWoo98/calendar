package com.choi.calender.application.dto.event;


import com.choi.calender.domain.api.event.EventBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.DateUtils;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {
    protected int no;
    protected String title;
    protected Date eventDate;
    protected String type;
    protected String holidayYn;
    protected String repeatYn;
    protected String deleteYn;
    protected Date regDate;

    public EventDto(
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

    public EventDto convertBeanToDto(EventBean eventBean) {
        return new EventDto(
            eventBean.getNo(),
            eventBean.getTitle(),
            eventBean.getEventDate(),
            eventBean.getType(),
            eventBean.getHolidayYn(),
            eventBean.getRepeatYn(),
            eventBean.getDeleteYn(),
            eventBean.getRegDate()
        );
    }

    public boolean isDataEmptyCheck() {
        return
            StringUtils.isBlank(this.title)
                || ObjectUtils.isEmpty(this.eventDate)
                || StringUtils.isBlank(this.type)
                || StringUtils.isBlank(this.repeatYn)
            ;
    }

}
