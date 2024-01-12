package com.choi.calender.mapper;

import com.choi.calender.domain.api.DiaryBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiaryMapper {

    int insertDiary(DiaryBean diaryBean);

    int updateDiary(DiaryBean diaryBean);

    DiaryBean selectDiary(String diaryDate);

}