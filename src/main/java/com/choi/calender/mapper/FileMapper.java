package com.choi.calender.mapper;

import com.choi.calender.application.dto.event.SearchFileBean;
import com.choi.calender.domain.api.file.FileBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    List<FileBean> selectDiaryFiles(SearchFileBean searchFileBean);

    int insertFiles(List<FileBean> list);

    int deleteFile(String keyNo);

}