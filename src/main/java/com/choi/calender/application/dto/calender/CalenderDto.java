package com.choi.calender.application.dto.calender;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public class CalenderDto {
    protected String test1;
    protected String test2;
    protected String test3;
    protected String test4;
    protected String test5;
    protected String test6;

    public CalenderDto(
            String test1,
            String test2,
            String test3,
            String test4,
            String test5,
            String test6
    ) {
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
        this.test4 = test4;
        this.test5 = test5;
        this.test6 = test6;
    }

    public boolean isDataEmptyCheck(CalenderDto calenderDto) {
        return
            StringUtils.isBlank(calenderDto.getTest1())
            || StringUtils.isBlank(calenderDto.getTest2())
            || StringUtils.isBlank(calenderDto.getTest3())
            || StringUtils.isBlank(calenderDto.getTest4())
            || StringUtils.isBlank(calenderDto.getTest5())
            || StringUtils.isBlank(calenderDto.getTest6())
        ;
    }
}
