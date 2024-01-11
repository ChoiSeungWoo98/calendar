package com.choi.calender.controller.view;


import com.choi.calender.application.calender.dto.TargetDto;
import com.choi.calender.application.calender.service.CalenderService;
import com.choi.calender.domain.api.SearchTargetBean;
import com.choi.calender.domain.api.TargetBean;
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
    CalenderService calenderService;

    @GetMapping("/main")
    public String main(Model model, HttpServletRequest request) {
//        if(!xeSessionUtils.isSessionAttributeNotExists(request)) {
//            return "redirect:/main";
//        }
        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear());
        String month = String.valueOf(currentDate.getMonthValue());

        SearchTargetBean searchYearTargetBean = new SearchTargetBean("Y", year, month);
        List<TargetDto> yearList = calenderService.selectYearTarget(searchYearTargetBean);
        SearchTargetBean searchMonthTargetDto = new SearchTargetBean("M", year, month);
        List<TargetDto> monthList = calenderService.selectMonthTarget(searchMonthTargetDto);

        model.addAttribute("view", "main");
        model.addAttribute("debugCheck", common.isDebugModeCheck());
        return "page/main";
    }

    @GetMapping("/detail")
    public String detail(
            Model model,
             @RequestParam(name = "year") int year,
             @RequestParam(name = "month") int month,
             @RequestParam(name = "day") int day,
             @RequestParam(name = "weekDay") String weekDay,
             HttpServletRequest request
    ) {
        model.addAttribute("view", "detail");
        setModelDate(model, year, month, day, weekDay);
        return "page/detail";
    }

    @GetMapping("/error/{errorNum}")
    public String errorPage(Model model, @PathVariable int errorNum) {
        return "error/" + errorNum;
    }

    private void setModelDate(Model model, int year, int month, int day, String weekDay) {
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("weekDay", weekDay);
    }

}
