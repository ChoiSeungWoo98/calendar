package com.choi.calender.application.dto.file;


import com.choi.calender.domain.api.file.FileBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class FileDto {
    protected int no;
    protected String keyNo;
    protected String identifier;
    protected String oriFileName;
    protected String fileName;
    protected String ext;
    protected long size;
    protected Instant regDate;

    public FileDto(
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

    public FileDto convertBeanToDto(FileBean fileBean) {
        return new FileDto(
                fileBean.getNo(),
                fileBean.getKeyNo(),
                fileBean.getIdentifier(),
                fileBean.getOriFileName(),
                fileBean.getFileName(),
                fileBean.getExt(),
                fileBean.getSize(),
                fileBean.getRegDate()
        );
    }
}
