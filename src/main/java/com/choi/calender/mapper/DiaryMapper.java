package com.choi.calender.mapper;

import com.choi.calender.domain.api.DiaryBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryMapper {

    int insertDiary(DiaryBean diaryBean);

    int updateDiary(DiaryBean diaryBean);

    List<DiaryBean> selectThisMonthDiaryList(String diaryDate);

    DiaryBean selectDiary(String diaryDate);

}