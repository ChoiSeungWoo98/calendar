package com.choi.calender.controller.api;

import com.choi.calender.application.calender.dto.DiaryDto;
import com.choi.calender.application.calender.dto.TargetDto;
import com.choi.calender.application.calender.service.CalenderService;
import com.choi.calender.domain.value.ReturnStatus;
import com.choi.calender.util.ReturnMessage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calender")
@Slf4j
public class TargetController {

    @Resource
    CalenderService calenderService;

    @PostMapping("/diary/add")
    public ReturnMessage addDiary(
            @ModelAttribute("diaryDto") DiaryDto diaryDto
    ) {
        if(diaryDto.isDataEmptyCheck()) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }

        try {
            return new ReturnMessage(calenderService.insertDiary(diaryDto));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "일기 저장 실패", e);
        }
    }

    @PostMapping("/targer/add")
    public ReturnMessage addTarget(
            @ModelAttribute("targetDto") TargetDto targetDto
    ) {
        if(checkValue(targetDto)) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }

        try {
            return new ReturnMessage(calenderService.insertTarget(targetDto));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "목표 저장 실패", e);
        }
    }

    public boolean checkValue(TargetDto targetDto) {
        if("Y".equals(targetDto.getType())) return targetDto.isYearDataEmptyCheck();
        if("M".equals(targetDto.getType())) return targetDto.isMonthDataEmptyCheck();
        if("D".equals(targetDto.getType())) return targetDto.isDayDataEmptyCheck();
        return true;
    }

}
