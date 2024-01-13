package com.choi.calender.domain.api.file;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class FileBean {
    protected int no;
    protected String keyNo;
    protected String identifier;
    protected String oriFileName;
    protected String fileName;
    protected String ext;
    protected long size;
    protected Instant regDate;

    public FileBean(
        int no,
        String keyNo,
        String identifier,
        String oriFileName,
        String fileName,
        String ext,
        long size,
        Instant regDate
    ) {
        this.no = no;
        this.keyNo = keyNo;
        this.identifier = identifier;
        this.oriFileName = oriFileName;
        this.fileName = fileName;
        this.ext = ext;
        this.size = size;
        this.regDate = regDate;
    }

    public FileBean(
        String keyNo,
        String identifier,
        String oriFileName,
        String fileName,
        String ext,
        long size
    ) {
        this.keyNo = keyNo;
        this.identifier = identifier;
        this.oriFileName = oriFileName;
        this.fileName = fileName;
        this.ext = ext;
        this.size = size;
    }
}