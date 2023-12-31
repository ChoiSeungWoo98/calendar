package com.choi.calender.controller.view;


import com.choi.calender.util.Common;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ViewController {

    @Resource
    Common common;

    @GetMapping("/main")
    public String main(Model model, HttpServletRequest request) {
//        if(!xeSessionUtils.isSessionAttributeNotExists(request)) {
//            return "redirect:/main";
//        }
        model.addAttribute("debugCheck", common.isDebugModeCheck());
        return "page/main";
    }

    @GetMapping("/detail")
    public String detail(
            Model model,
             @RequestParam(name = "year") int year,
             @RequestParam(name = "month") int month,
             @RequestParam(name = "day") int day,
             HttpServletRequest request
    ) {
        setModelDate(model, year, month, day);
        return "page/detail";
    }

    @GetMapping("/error/{errorNum}")
    public String errorPage(Model model, @PathVariable int errorNum) {
        return "error/" + errorNum;
    }

    private void setModelDate(Model model, int year, int month, int day) {
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
    }

}
