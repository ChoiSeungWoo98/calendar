package com.choi.calender.controller.api;

import com.choi.calender.application.dto.diary.DiaryDto;
import com.choi.calender.application.service.DiaryService;
import com.choi.calender.application.service.FileService;
import com.choi.calender.domain.value.ReturnStatus;
import com.choi.calender.util.ReturnMessage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/calender/diary")
@Slf4j
public class DiaryController {

    @Resource
    DiaryService diaryService;

    @Resource
    FileService fileService;

    @PostMapping("/add")
    public ReturnMessage addDiary(
            @ModelAttribute("diaryDto") DiaryDto diaryDto
    ) {
        if(diaryDto.isDataEmptyCheck()) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }

        try {
            return new ReturnMessage(diaryService.insertDiary(diaryDto));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "일기 저장 실패", e);
        }
    }

    @PutMapping("/modify")
    public ReturnMessage modifyDiary(
            @ModelAttribute("diaryDto") DiaryDto diaryDto
    ) {
        if(diaryDto.isUpdateDataEmptyCheck()) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }

        try {
            return new ReturnMessage(diaryService.updateDiary(diaryDto));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "일기 저장 실패", e);
        }
    }

    @GetMapping("/find/month")
    public ReturnMessage findMonthDiary(
            @ModelAttribute("diaryDto") DiaryDto diaryDto
    ) {
        if(StringUtils.isBlank(diaryDto.getDiaryDate())) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }

        try {
            return new ReturnMessage(diaryService.selectThisMonthDiaryList(diaryDto.getDiaryDate()));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "일기 저장 실패", e);
        }
    }

    @PostMapping("/add/file")
    public ReturnMessage addFile(
            MultipartHttpServletRequest request,
            @RequestParam("no") String no
    ) {
        if(StringUtils.isBlank(no)) {
            return new ReturnMessage(ReturnStatus.NO_VALUE, "필수 값이 존재하지 않습니다.", new Exception("필수 값이 존재하지 않습니다."));
        }
        List<MultipartFile> files = request.getFiles("file");

        try {
            fileService.deleteFile(no);
            if(files == null) {
                throw new IOException("파일이 존재하지 않습니다.");
            }

            return new ReturnMessage(fileService.insertFile(files, no));
        } catch (Exception e) {
            return new ReturnMessage(ReturnStatus.FAIL, "파일 저장 실패", e);
        }
    }

}
