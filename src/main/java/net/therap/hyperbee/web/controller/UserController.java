package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.*;
import net.therap.hyperbee.utils.Utils;
import net.therap.hyperbee.web.command.SignUpDto;
import net.therap.hyperbee.web.helper.NoticeHelper;
import net.therap.hyperbee.web.helper.ReservationHelper;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.validator.LoginValidator;
import net.therap.hyperbee.web.validator.SignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static net.therap.hyperbee.utils.constant.Messages.LOGGED_IN;
import static net.therap.hyperbee.utils.constant.Messages.SIGNED_UP;

/**
 * @author rayed
 * @since 11/22/16 10:59 AM
 */
@Controller
public class UserController {

    private static final String ROOT_URL = "/";

    private static final String LOGIN_URL = "/login";

    private static final String LOGIN_VIEW = "user/login";

    private static final String LOGOUT_URL = "/logout";

    private static final String SIGN_UP_URL = "/signUp";

    private static final String SIGN_UP_VIEW = "user/signUp";

    private static final String USER_DASHBOARD_VIEW = "dashboard";

    private static final String USER_DASHBOARD_URL = "/user/dashboard";

    private static final String USER_ACTIVATE_URL = "/user/activate/{userId}";

    private static final String USER_DEACTIVATE_URL = "/user/deactivate/{userId}";

    private static final String PROFILE_URL = "/profile";

    private static final String SEARCH_URL = "/search";

    @Autowired
    BuzzService buzzService;

    @Autowired
    NoteService noteService;

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private SignUpValidator signUpValidator;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private NoticeHelper noticeHelper;

    @Autowired
    private ReservationHelper reservationHelper;

    @Autowired
    private HiveService hiveService;

    @InitBinder("login")
    private void loginValidator(WebDataBinder binder) {
        binder.setValidator(loginValidator);
    }

    @InitBinder("signUp")
    private void signUpValidator(WebDataBinder binder) {
        binder.setValidator(signUpValidator);
    }

    @GetMapping(ROOT_URL)
    public String entry() {

        return Utils.redirectTo(LOGIN_URL);
    }

    @GetMapping(LOGIN_URL)
    public String login(Model model) {
        model.addAttribute("login", new User());

        return LOGIN_VIEW;
    }

    @PostMapping(LOGIN_URL)
    public String loginUser(@Validated @ModelAttribute("login") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return LOGIN_VIEW;
        }

        User retrievedUser = userService.findByUsernameAndPassword(user);

        if ((retrievedUser != null) && (retrievedUser.getDisplayStatus() == DisplayStatus.ACTIVE)) {
            sessionHelper.persistInSession(retrievedUser);
            noticeHelper.persistInSession();
            reservationHelper.persistInSession();

            activityService.archive(LOGGED_IN);

            return Utils.redirectTo(USER_DASHBOARD_URL);
        }

        return LOGIN_VIEW;
    }

    @GetMapping(SIGN_UP_URL)
    public String signUp(Model model) {
        model.addAttribute("signUp", new SignUpDto());

        return SIGN_UP_VIEW;
    }

    @PostMapping(SIGN_UP_URL)
    public String signUpDash(@Validated @ModelAttribute("signUp") SignUpDto signUpDto,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return SIGN_UP_VIEW;
        }

        User user = signUpDto.getUser();

        User retrievedUser = userService.createUser(user);
        sessionHelper.persistInSession(retrievedUser);

        Hive hive = hiveService.retrieveHiveById(1);
        hiveService.insertFirstUserToHive(hive, retrievedUser.getId());

        activityService.archive(SIGNED_UP);

        return Utils.redirectTo(USER_DASHBOARD_URL);
    }

    @GetMapping(LOGOUT_URL)
    public String logout() {
        sessionHelper.invalidateSession();

        return Utils.redirectTo(LOGIN_URL);
    }

    @GetMapping(USER_DASHBOARD_URL)
    public String welcome(Model model) {
        if (!model.containsAttribute("newBuzz")) {
            model.addAttribute("newBuzz", new Buzz());
        }

        int userId = sessionHelper.getUserIdFromSession();

        model.addAttribute("topStickyNote",
                noteService.findTopStickyNoteByUser(userId));
        model.addAttribute("latestReminders",
                noteService.findUpcomingReminderNoteByUser(userId));

        model.addAttribute("pinnedBuzzList", buzzService.getPinnedBuzz());
        model.addAttribute("buzzList", buzzService.getLatestBuzz());

        sessionHelper.setStatInSession();

        return USER_DASHBOARD_VIEW;
    }

    @GetMapping(USER_DEACTIVATE_URL)
    public String inactivateUser(@PathVariable int userId) {
        userService.inactivate(userId);

        sessionHelper.decrementSessionAttribute("activeUsers", 1);
        sessionHelper.incrementSessionAttribute("inactiveUsers", 1);

        return Utils.redirectTo(PROFILE_URL + SEARCH_URL);
    }

    @GetMapping(USER_ACTIVATE_URL)
    public String activateUser(@PathVariable int userId) {
        userService.activate(userId);

        sessionHelper.incrementSessionAttribute("activeUsers", 1);
        sessionHelper.decrementSessionAttribute("inactiveUsers", 1);

        return Utils.redirectTo(PROFILE_URL + SEARCH_URL);
    }
}
