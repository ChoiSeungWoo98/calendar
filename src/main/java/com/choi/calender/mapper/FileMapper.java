package com.choi.calender.mapper;

import com.choi.calender.domain.api.file.FileBean;
import com.choi.calender.domain.api.file.SearchFileBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    List<FileBean> selectDiaryFiles(SearchFileBean searchFileBean);

    int insertFiles(List<FileBean> list);

    int deleteFile(String keyNo);

}