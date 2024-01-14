package com.choi.calender.application.impl;

import com.choi.calender.application.dto.file.FileDto;
import com.choi.calender.application.service.FileService;
import com.choi.calender.domain.api.file.FileBean;
import com.choi.calender.application.dto.event.SearchFileBean;
import com.choi.calender.domain.value.FileIdentifier;
import com.choi.calender.mapper.FileMapper;
import com.choi.calender.util.Common;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    Common common;

    @Resource
    private FileMapper fileMapper;

    @Override
    public List<FileDto> selectDiaryFiles(SearchFileBean searchFileBean) {
        return fileMapper.selectDiaryFiles(searchFileBean).stream().map(file -> new FileDto().convertBeanToDto(file)).collect(Collectors.toList());
    }

    @Override
    public String insertFile(List<MultipartFile> files, String keyNo) throws IOException {
        String identifier = FileIdentifier.Diary.getValue();
        String path = common.checkAndCreateForder("file" + File.separator + identifier + File.separator + keyNo);
        List<FileBean> fileList = new ArrayList<>();

        files.forEach(file -> {
            try {
                String uniqueFileName = common.saveMultipartFile(file, path);
                int subIdx = uniqueFileName.lastIndexOf(".");
                fileList.add(
                    new FileBean(
                        keyNo,
                        identifier,
                        file.getOriginalFilename(),
                        uniqueFileName.substring(0, subIdx),
                        uniqueFileName.substring(subIdx + 1),
                        file.getSize()
                    )
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return fileMapper.insertFiles(fileList) >= 1
                ? "파일 저장에 성공했습니다!"
                : "저장에 실패했습니다. 코드를 수정해주세요.";
    }

    @Override
    public boolean deleteFile(String keyNo) throws IOException {
        deleteRealFolder(keyNo);
        return fileMapper.deleteFile(keyNo) == 1 ? true : false;
    }

    public void deleteRealFolder(String keyNo) throws IOException {
        String identifier = FileIdentifier.Diary.getValue();
        String path = common.checkAndCreateForder("file" + File.separator + identifier + File.separator + keyNo);
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