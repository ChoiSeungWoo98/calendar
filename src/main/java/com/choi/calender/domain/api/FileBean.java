package com.xe.management.domain.file;

import com.xe.management.application.dto.file.FileDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class FileBean {
    protected int no;
    protected int key;
    protected String oriFileName;
    protected String fileName;
    protected String ext;
    protected long size;
    protected Instant regDate;

    public FileBean(
        int key,
        String oriFileName,
        String fileName,
        String ext,
        long size
    ) {
        this.key = key;
        this.oriFileName = oriFileName;
        this.fileName = fileName;
        this.ext = ext;
        this.size = size;
    }

    public FileBean convertFileDto(FileDto fileDto) {
        return new FileBean(
                fileDto.getKey(),
                fileDto.getOriFileName(),
                fileDto.getFileName(),
                fileDto.getExt(),
                fileDto.getSize()
        );
    }

}