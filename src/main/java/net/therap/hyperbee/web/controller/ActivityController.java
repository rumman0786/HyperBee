package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Activity;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.web.helper.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author rayed
 * @since 11/28/16 11:07 AM
 */
@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private SessionHelper sessionHelper;

    @GetMapping("/user/activity/log")
    public String viewActivity(Model model, HttpSession session) {
        int userId = sessionHelper.getUserIdFromSession();

        List<Activity> activityList = activityService.findByUserId(userId);
        model.addAttribute("activityList", activityList);

        return "activity/log";
    }
}
