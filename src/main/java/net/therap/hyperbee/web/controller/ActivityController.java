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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static net.therap.hyperbee.utils.constant.Url.ACTIVITY_ROOT_URL;

/**
 * @author rayed
 * @since 11/28/16 11:07 AM
 */
@Controller
@RequestMapping(ACTIVITY_ROOT_URL)
public class ActivityController {

    private static final String ACTIVITY_LOG_URL = "/log";

    private static final String ACTIVITY_VIEW = "activity/log";

    private static final String ACTIVITY_ADMIN_VIEW = "activity/userLog";

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionHelper sessionHelper;

    @GetMapping(ACTIVITY_LOG_URL)
    public String viewActivity(Model model) {
        int userId = sessionHelper.getAuthUserIdFromSession();

        List<Activity> activityList = activityService.findByUserId(userId);
        List<User> userList = userService.findAll();

        model.addAttribute("activityList", activityList);
        model.addAttribute("userInfo", new UserInfo(userList));
        model.addAttribute("page", "activity");

        return ACTIVITY_VIEW;
    }

    @PostMapping(ACTIVITY_LOG_URL)
    public String selectActivity(UserInfo userInfo, BindingResult bindingResult, Model model) {
        int userId = userInfo.getUserId();

        User user = userService.findById(userId);
        List<Activity> activityList = activityService.findByUserId(userId);

        model.addAttribute("user", user);
        model.addAttribute("activityList", activityList);
        model.addAttribute("page", "activity");

        return ACTIVITY_ADMIN_VIEW;
    }
}
