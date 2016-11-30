package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Activity;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.command.UserInfo;
import net.therap.hyperbee.web.helper.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    private UserService userService;

    @Autowired
    private SessionHelper sessionHelper;

    @GetMapping("/user/activity/log")
    public String viewActivity(Model model) {
        int userId = sessionHelper.getUserIdFromSession();

        List<Activity> activityList = activityService.findByUserId(userId);
        model.addAttribute("activityList", activityList);

        List<User> userList = userService.findAll();
        model.addAttribute("userInfo", new UserInfo(userList));

        return "activity/log";
    }

    @PostMapping("/user/activity/log")
    public String selectActivity(UserInfo userInfo, BindingResult bindingResult, Model model){
//        System.out.println(userInfo.getUserId());
        int userId = userInfo.getUserId();

        User user = userService.findById(userId);
        model.addAttribute("user", user);

        List<Activity> activityList = activityService.findByUserId(userId);
        model.addAttribute("activityList", activityList);

        return "activity/userLog";
    }
}
