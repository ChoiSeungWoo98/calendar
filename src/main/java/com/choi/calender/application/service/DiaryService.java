package com.choi.calender.application.service;

import com.choi.calender.application.dto.diary.DiaryDto;

import java.util.List;

public interface DiaryService {

    String insertDiary(DiaryDto diaryDto);

    String updateDiary(DiaryDto diaryDto);

    List<DiaryDto> selectThisMonthDiaryList(String diaryDate);

    DiaryDto selectDiary(String diaryDate);

}
