package com.choi.calender.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    int insertFile(String no);

    int deleteFile(String no);

}