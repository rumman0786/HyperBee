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
import net.therap.hyperbee.web.validator.UserEditValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static net.therap.hyperbee.utils.constant.Constant.SESSION_KEY_AUTH_USER;
import static net.therap.hyperbee.utils.constant.Messages.LOGGED_IN;
import static net.therap.hyperbee.utils.constant.Messages.SIGNED_UP;
import static net.therap.hyperbee.utils.constant.Url.*;

/**
 * @author rayed
 * @since 11/22/16 10:59 AM
 */
@SessionAttributes("userEdit")
@Controller
public class UserController {

    private static final Logger log = LogManager.getLogger(UserController.class);

    private static final String LOGIN_VIEW = "user/login";
    private static final String SIGN_UP_VIEW = "user/signUp";
    private static final String USER_DASHBOARD_VIEW = "dashboard";
    private static final String USER_EDIT_VIEW = "user/edit";

    private static final String ROOT_URL = "/";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_URL = "/logout";
    private static final String SIGN_UP_URL = "/signUp";
    private static final String USER_ACTIVATE_URL = "/user/activate/{userId}/{username}";
    private static final String USER_DEACTIVATE_URL = "/user/deactivate/{userId}/{username}";
    private static final String MAKE_ADMIN_URL = "/user/make/admin/{userId}";
    private static final String MAKE_USER_URL = "/user/make/user/{userId}";
    private static final String USER_EDIT_URL = "/user/edit";

    private static final int ADMIN_ROLE_ID = 1;
    private static final int USER_ROLE_ID = 2;

    @Autowired
    private BuzzService buzzService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private SignUpValidator signUpValidator;

    @Autowired
    private UserEditValidator userEditValidator;

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
        binder.addValidators(loginValidator);
    }

    @InitBinder("signUp")
    private void signUpValidator(WebDataBinder binder) {
        binder.addValidators(signUpValidator);
    }

    @InitBinder("userEdit")
    private void userEditValidator(WebDataBinder binder) {
        binder.addValidators(userEditValidator);
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
    public String loginUser(@Valid @ModelAttribute("login") User loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return LOGIN_VIEW;
        }

        User user = userService.findByUsername(loginUser.getUsername());

        sessionHelper.setSessionAttribute(SESSION_KEY_AUTH_USER, user.getAuthUser());
        sessionHelper.initializeNoteStatForUser();
        noticeHelper.updateNoticeCache();
        reservationHelper.updateReservationCache();

        activityService.archive(LOGGED_IN);

        return Utils.redirectTo(USER_DASHBOARD_URL);
    }

    @GetMapping(SIGN_UP_URL)
    public String signUp(Model model) {
        model.addAttribute("signUp", new SignUpDto());

        return SIGN_UP_VIEW;
    }

    @PostMapping(SIGN_UP_URL)
    public String signUpDash(@Valid @ModelAttribute("signUp") SignUpDto signUpDto,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return SIGN_UP_VIEW;
        }

        User user = signUpDto.getUser();
        User retrievedUser = userService.saveOrUpdate(user);

        Hive hive = hiveService.retrieveHiveById(1);
        hiveService.saveFirstUserToHive(hive, retrievedUser.getId());

        activityService.archive(SIGNED_UP);

        return Utils.redirectTo(USER_DASHBOARD_URL);
    }

    @GetMapping(LOGOUT_URL)
    public String logout() {
        sessionHelper.invalidateSession();

        return Utils.redirectTo(LOGIN_URL);
    }

    @GetMapping(USER_DASHBOARD_URL)
    public String welcome(ModelMap map) {
        if (!map.containsAttribute("newBuzz")) {
            map.put("newBuzz", new Buzz());
        }

        int userId = sessionHelper.getAuthUserIdFromSession();

        map.put("topStickyNote", noteService.findTopStickyNoteByUser(userId));
        map.put("latestReminders", noteService.findUpcomingReminderNoteByUser(userId));
        map.put("pinnedBuzzList", buzzService.getLatestPinnedBuzz());
        map.put("buzzList", buzzService.getLatestBuzz());

        sessionHelper.setSessionAttributes();

        return USER_DASHBOARD_VIEW;
    }

    @GetMapping(USER_EDIT_URL)
    public String serveUserEditForm(ModelMap map) {
        int userIdFromSession = sessionHelper.getAuthUserIdFromSession();

        User user = userService.findById(userIdFromSession);
        user.setPassword("");

        map.put("userEdit", user);

        return USER_EDIT_VIEW;
    }

    @PostMapping(USER_EDIT_URL)
    public String submitUserEditForm(@Valid @ModelAttribute("userEdit") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return USER_EDIT_VIEW;
        }

        userService.saveOrUpdate(user);

        return Utils.redirectTo(USER_DASHBOARD_URL);
    }

    @PostMapping(MAKE_ADMIN_URL)
    public String makeUserAdmin(@PathVariable int userId) {

        return changeRole(userId, ADMIN_ROLE_ID);
    }

    @PostMapping(MAKE_USER_URL)
    public String makeAdminUser(@PathVariable int userId) {

        return changeRole(userId, USER_ROLE_ID);
    }

    @PostMapping(USER_DEACTIVATE_URL)
    public String inactivateUser(@PathVariable int userId, @PathVariable String username) {

        return changeDisplayStatus(userId, username, DisplayStatus.INACTIVE);
    }

    @PostMapping(USER_ACTIVATE_URL)
    public String activateUser(@PathVariable int userId, @PathVariable String username) {

        return changeDisplayStatus(userId, username, DisplayStatus.ACTIVE);
    }

    private String changeDisplayStatus(int userId, String username, DisplayStatus status) {
        userService.updateStatus(userId, username, status);

        return Utils.redirectTo(PROFILE_URL + SEARCH_URL);
    }

    private String changeRole(int userId, int role) {
        userService.updateRole(userId, role);

        return Utils.redirectTo(PROFILE_URL + SEARCH_URL);
    }
}

