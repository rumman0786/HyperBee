package net.therap.hyperbee.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import static net.therap.hyperbee.utils.constant.Messages.NOTICE_ACCESS_DENIED;
import static net.therap.hyperbee.utils.constant.Url.DONE_URL;
import static net.therap.hyperbee.utils.constant.Url.DONE_VIEW;

/**
 * @author rumman
 * @since 11/30/16
 */
@Controller
public class HomeController {

    @GetMapping(value = DONE_URL)
    public String noticeAccessDenied(Model model) {
        //modelMap.addAttribute("message", NOTICE_ACCESS_DENIED);
        //modelMap.addAttribute("messageStyle", "alert alert-success");
        return DONE_VIEW;
    }
}
