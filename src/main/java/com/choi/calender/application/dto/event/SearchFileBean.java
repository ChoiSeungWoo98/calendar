package com.choi.calender.application.dto.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchFileBean {
    protected int keyNo;
    protected String identifier;

    public SearchFileBean(
        int keyNo,
        String identifier
    ) {
        this.keyNo = keyNo;
        this.identifier = identifier;
    }

}