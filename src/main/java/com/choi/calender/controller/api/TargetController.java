package com.choi.calender.controller.api;

import com.choi.calender.application.dto.target.TargetDto;
import com.choi.calender.application.dto.target.TodoTargetDto;
import com.choi.calender.application.service.TargetService;
import com.choi.calender.domain.value.ReturnStatus;
import com.choi.calender.util.ReturnMessage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calender/target")
@Slf4j
public class TargetController {

    @Resource
    TargetService targetService;

    @PostMapping("/add")
    public ReturnMessage addTarget(
            @ModelAttribute("targetDto") TargetDto targetDto
    ) {
        if(isCheckInsertValue(targetDto)) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }

        try {
            return new ReturnMessage(targetService.insertTarget(targetDto));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "목표 저장 실패", e);
        }
    }

    @PutMapping("/update/successYn")
    public ReturnMessage updateSuccessYn(
            @ModelAttribute("targetDto") TargetDto targetDto
    ) {
        if(isCheckSuccessData(targetDto)) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }

        try {
            return new ReturnMessage(targetService.updateSuccessYn(targetDto));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "목표 저장 실패", e);
        }
    }

    @PostMapping("/insert/todo/successYn")
    public ReturnMessage insertTodoSuccessYn(
            @ModelAttribute("todoTargetDto") TodoTargetDto todoTargetDto
    ) {
        if(todoTargetDto.isDataEmptyCheck()) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }

        try {
            return new ReturnMessage(targetService.insertTodoSuccessYn(todoTargetDto));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "목표 저장 실패", e);
        }
    }

    public boolean isCheckInsertValue(TargetDto targetDto) {
        if("Y".equals(targetDto.getType())) return targetDto.isYearDataEmptyCheck();
        if("M".equals(targetDto.getType())) return targetDto.isMonthDataEmptyCheck();
        if("D".equals(targetDto.getType())) return targetDto.isDayDataEmptyCheck();
        return true;
    }

    public boolean isCheckSuccessData(TargetDto targetDto) {
        if(targetDto.getNo() == 0 || StringUtils.isBlank(targetDto.getSuccessYn())) return true;
        return false;
    }

}
