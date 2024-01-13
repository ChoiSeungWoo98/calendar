package com.choi.calender.application.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    String insertFile(List<MultipartFile> file, String no) throws IOException;

    boolean deleteFile(String no) throws IOException;

}
