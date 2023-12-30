package com.choi.calender.controller.view;


import com.choi.calender.util.Common;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class ViewController {

    @Resource
    Common common;

    @GetMapping("/main")
    public String login(Model model, HttpServletRequest request) {
//        if(!xeSessionUtils.isSessionAttributeNotExists(request)) {
//            return "redirect:/main";
//        }
        model.addAttribute("debugCheck", common.isDebugModeCheck());
        return "page/main.html";
    }

    @GetMapping("/error/{errorNum}")
    public String errorPage(Model model, @PathVariable int errorNum) {
        return "error/" + errorNum;
    }

}
