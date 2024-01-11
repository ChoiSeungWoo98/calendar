package com.choi.calender.controller.api;

import com.choi.calender.application.calender.dto.DiaryDto;
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
public class CalenderController {

    @Resource
    CalenderService calenderService;

    @PostMapping("/diary/add")
    public ReturnMessage addDiary(
            @ModelAttribute("diaryDto") DiaryDto diaryDto
    ) {
        if(diaryDto.isDataEmptyCheck(diaryDto)) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }

        try {
            return new ReturnMessage(calenderService.insertDiary(diaryDto));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "유저 생성 실패", e);
        }
    }

    @PostMapping("/targer/add")
    public ReturnMessage addTarget(
            @ModelAttribute("userDto") DiaryDto diaryDto
    ) {
        if(diaryDto.isDataEmptyCheck(diaryDto)) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }

        try {
            return new ReturnMessage(calenderService.insertDiary(diaryDto));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "유저 생성 실패", e);
        }
    }

}
