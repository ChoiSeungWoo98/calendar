package com.choi.calender.application.service;

import java.io.IOException;

public interface FileService {

    String insertFile(String no);

    boolean deleteFile(String no) throws IOException;

}
