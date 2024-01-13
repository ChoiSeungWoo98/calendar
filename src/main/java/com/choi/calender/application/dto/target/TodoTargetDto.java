package com.choi.calender.application.dto.target;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public class TodoTargetDto {
    protected int no;
    protected String targetNo;
    protected String diaryNo;
    protected String successYn;

    public TodoTargetDto(
        int no,
        String targetNo,
        String diaryNo,
        String successYn
    ) {
        this.no = no;
        this.targetNo = targetNo;
        this.diaryNo = diaryNo;
        this.successYn = successYn;
    }

    public boolean isDataEmptyCheck() {
        return
            StringUtils.isBlank(this.targetNo)
            || StringUtils.isBlank(this.diaryNo)
            || StringUtils.isBlank(this.successYn)
        ;
    }

}
