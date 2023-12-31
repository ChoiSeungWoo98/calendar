package com.choi.calender.controller.api;

import com.choi.calender.application.dto.calender.CalenderDto;
import com.choi.calender.application.service.CalenderService;
import com.choi.calender.domain.value.ReturnStatus;
import com.choi.calender.util.ReturnMessage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calender/work")
@Slf4j
public class CalenderController {

    @Resource
    CalenderService calenderService;

    @PostMapping("/add")
    public ReturnMessage join(
            @ModelAttribute("userDto") CalenderDto calenderDto
    ) {
        if(calenderDto.isDataEmptyCheck(calenderDto)) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }

        try {
            return new ReturnMessage(calenderService.insertCalenderData(calenderDto));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "유저 생성 실패", e);
        }
    }

}
