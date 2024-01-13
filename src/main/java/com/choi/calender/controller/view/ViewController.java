package com.choi.calender.controller.view;


import com.choi.calender.application.dto.diary.DiaryDto;
import com.choi.calender.application.dto.target.TargetDto;
import com.choi.calender.application.service.DiaryService;
import com.choi.calender.application.service.TargetService;
import com.choi.calender.domain.api.target.SearchTargetBean;
import com.choi.calender.util.Common;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
public class ViewController {

    @Resource
    Common common;

    @Resource
    TargetService targetService;

    @Resource
    DiaryService diaryService;

    @GetMapping("/main")
    public String main(Model model) {
        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear());
        String month = String.valueOf(currentDate.getMonthValue());

        SearchTargetBean searchYearTargetBean = new SearchTargetBean("Y", year, null);
        SearchTargetBean searchMonthTargetBean = new SearchTargetBean("M", year, month);
        List<TargetDto> yearList = targetService.selectTarget(searchYearTargetBean);
        List<TargetDto> monthList = targetService.selectTarget(searchMonthTargetBean);

        model.addAttribute("yearList", yearList);
        model.addAttribute("monthList", monthList);
        model.addAttribute("view", "main");
        model.addAttribute("debugCheck", common.isDebugModeCheck());
        return "page/main";
    }

    @GetMapping("/detail")
    public String detail(
            Model model,
             @RequestParam(name = "year") String year,
             @RequestParam(name = "month") String month,
             @RequestParam(name = "day") String day,
             @RequestParam(name = "weekDay") String weekDay,
             HttpServletRequest request
    ) {
        String diaryDate = year + "-" + month + "-" + day;
        DiaryDto diary = diaryService.selectDiary(diaryDate);
        int diaryNo = 0;
        if(diary != null) {
            diaryNo = diary.getNo();
        }
        SearchTargetBean searchTargetBean = new SearchTargetBean("D", year, month, day, diaryNo);
        List<TargetDto> todoList = targetService.selectRepeatTodoTarget(searchTargetBean);

        model.addAttribute("diary", diary);
        model.addAttribute("todoList", todoList);
        model.addAttribute("view", "detail");
        setModelDate(model, year, month, day, weekDay);
        return "page/detail";
    }

    @GetMapping("/error/{errorNum}")
    public String errorPage(Model model, @PathVariable int errorNum) {
        return "error/" + errorNum;
    }

    private void setModelDate(Model model, String year, String month, String day, String weekDay) {
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("weekDay", weekDay);
    }

}
