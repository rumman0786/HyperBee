package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.utils.constant.Messages;
import net.therap.hyperbee.utils.constant.Url;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import static net.therap.hyperbee.utils.constant.Messages.NOTICE_ACCESS_DENIED;
import static net.therap.hyperbee.utils.constant.Url.*;

/**
 * @author rumman
 * @since 11/30/16
 */
@Controller
public class HomeController {


    @GetMapping(value = ACCESS_DENIED_URL)
    public String noticeAccessDenied(ModelMap modelMap) {
        modelMap.addAttribute("message", NOTICE_ACCESS_DENIED);
        modelMap.addAttribute("messageStyle", "alert alert-success");
        return "done";
    }
}
