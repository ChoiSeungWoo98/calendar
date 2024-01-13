package com.choi.calender.domain.api.file;

import com.choi.calender.application.dto.file.FileDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class FileBean {
    protected int no;
    protected String key;
    protected String identifier;
    protected String oriFileName;
    protected String fileName;
    protected String ext;
    protected long size;
    protected Instant regDate;

    public FileBean(
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

    public FileBean convertFileDto(FileDto fileDto) {
        return new FileBean(
                fileDto.getKey(),
                fileDto.getIdentifier(),
                fileDto.getOriFileName(),
                fileDto.getFileName(),
                fileDto.getExt(),
                fileDto.getSize()
        );
    }

}