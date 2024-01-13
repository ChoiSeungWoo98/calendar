package com.choi.calender.application.impl;

import com.choi.calender.application.service.FileService;
import com.choi.calender.mapper.FileMapper;
import com.choi.calender.util.Common;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    Common common;

    @Resource
    private FileMapper fileMapper;

    @Override
    public String insertFile(String no) {
        return fileMapper.insertFile(no) == 1
                ? "오늘도 고생했지만 내일은 더 열심히 해보자!"
                : "저장에 실패했습니다. 코드를 수정해주세요.";
    }

    @Override
    public boolean deleteFile(String no) throws IOException {
        deleteRealFolder(no);
        return fileMapper.deleteFile(no) == 1 ? true : false;
    }

    public void deleteRealFolder(String no) throws IOException {
        String path = common.checkAndCreateForder("file" + File.separator + no);
        File deleteDirectory = new File(path);

        if (!deleteDirectory.exists()) return ;

        File[] files = deleteDirectory.listFiles();
        if (files == null) return ;

        for (File file : files) {
            file.delete();
        }

        deleteDirectory.delete();
    }

}