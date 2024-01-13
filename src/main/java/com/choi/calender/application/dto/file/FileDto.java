package com.choi.calender.application.dto.file;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class FileDto {
    protected int no;
    protected String key;
    protected String identifier;
    protected String oriFileName;
    protected String fileName;
    protected String ext;
    protected long size;
    protected Instant regDate;

    public FileDto(
        String key,
        String identifier,
        String oriFileName,
        String fileName,
        String ext,
        long size
    ) {
        this.key = key;
        this.identifier = identifier;
        this.oriFileName = oriFileName;
        this.fileName = fileName;
        this.ext = ext;
        this.size = size;
    }


}
