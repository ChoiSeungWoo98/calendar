package com.choi.calender.application.diary.service;

import com.choi.calender.application.diary.dto.DiaryDto;

import java.util.List;

public interface DiaryService {

    String insertDiary(DiaryDto diaryDto);

    String updateDiary(DiaryDto diaryDto);

    List<DiaryDto> selectThisMonthDiaryList(String diaryDate);

    DiaryDto selectDiary(String diaryDate);

}
