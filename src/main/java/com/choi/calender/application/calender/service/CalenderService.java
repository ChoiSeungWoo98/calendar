package com.choi.calender.application.calender.service;

import com.choi.calender.application.calender.dto.DiaryDto;
import com.choi.calender.application.calender.dto.TargetDto;

public interface CalenderService {

    String insertDiary(DiaryDto diaryDto);

    String insertTarget(TargetDto targetDto);

}
