package com.choi.calender.domain.api.file;

import com.choi.calender.application.dto.file.FileDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

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