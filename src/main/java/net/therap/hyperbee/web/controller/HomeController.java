package net.therap.hyperbee.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static net.therap.hyperbee.utils.constant.Url.ACCESS_DENIED_URL;

/**
 * @author rumman
 * @since 11/30/16
 */
@Controller
public class HomeController {


    @GetMapping(value = ACCESS_DENIED_URL)
    public String noticeAccessDenied(Model model) {
       /* modelMap.addAttribute("message", NOTICE_ACCESS_DENIED);
        modelMap.addAttribute("messageStyle", "alert alert-success");*/
        return "done";
    }
}
