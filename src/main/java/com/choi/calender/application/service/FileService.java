package com.choi.calender.application.service;

import com.choi.calender.application.dto.event.SearchFileBean;
import com.choi.calender.application.dto.file.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    List<FileDto> selectDiaryFiles(SearchFileBean searchFileBean);

    String insertFile(List<MultipartFile> file, String no) throws IOException;

    boolean deleteFile(String no) throws IOException;

}
