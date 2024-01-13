package com.choi.calender.domain.api.target;

import com.choi.calender.application.dto.target.TodoTargetDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoTargetBean {
    protected int no;
    protected String targetNo;
    protected String diaryNo;
    protected String successYn;


    public TodoTargetBean(
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

    public TodoTargetBean convertDtoToBean(TodoTargetDto todoTargetDto) {
        return new TodoTargetBean(
            todoTargetDto.getNo() == 0 ? 0 : todoTargetDto.getNo(),
            todoTargetDto.getTargetNo(),
            todoTargetDto.getDiaryNo(),
            todoTargetDto.getSuccessYn()
        );
    }
}