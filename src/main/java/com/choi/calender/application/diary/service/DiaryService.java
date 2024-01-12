package com.choi.calender.application.diary.service;

import com.choi.calender.application.diary.dto.DiaryDto;

public interface DiaryService {

    String insertDiary(DiaryDto diaryDto);

    String updateDiary(DiaryDto diaryDto);

    DiaryDto selectDiary(String diaryDate);

}
