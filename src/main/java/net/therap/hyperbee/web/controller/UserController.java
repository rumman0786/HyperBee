package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.StickyNoteService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.command.SignUpInfo;
import net.therap.hyperbee.web.helper.NoticeHelper;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.helper.UserHelper;
import net.therap.hyperbee.web.security.AuthUser;
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
import static net.therap.hyperbee.utils.constant.Url.*;

/**
 * @author rayed
 * @since 11/22/16 10:59 AM
 */
@Controller
public class UserController {

    @Autowired
    BuzzService buzzService;

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private SignUpValidator signUpValidator;

    @Autowired
    StickyNoteService noteService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private NoticeHelper noticeHelper;

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

        return "redirect:" + LOGIN_URL;
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

            activityService.archive(LOGGED_IN);

            noticeHelper.persistInSession();

            return "redirect:" + USER_DASHBOARD_URL;
        }

        return LOGIN_VIEW;
    }

    @GetMapping(SIGN_UP_URL)
    public String signUp(Model model) {
        model.addAttribute("signUp", new SignUpInfo());

        return SIGN_UP_VIEW;
    }

    @PostMapping(SIGN_UP_URL)
    public String signUpDash(@Validated @ModelAttribute("signUp") SignUpInfo signUpInfo,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return SIGN_UP_VIEW;
        }

        User user = signUpInfo.getUser();

        User retrievedUser = userService.createUser(user);
        sessionHelper.persistInSession(retrievedUser);

        activityService.archive(SIGNED_UP);

        return "redirect:" + USER_DASHBOARD_URL;
    }

    @GetMapping(LOGOUT_URL)
    public String logout() {
        sessionHelper.invalidateSession();

        return "redirect:" + LOGIN_URL;
    }

    @GetMapping("/user/dashboard")
    public String welcome(Model model) {
        if (!model.containsAttribute("newBuzz")) {
            model.addAttribute("newBuzz", new Buzz());
        }

        model.addAttribute("topStickyNote",
                noteService.findTopStickyNoteByUser(sessionHelper.getUserIdFromSession()));

        model.addAttribute("pinnedBuzzList", buzzService.getPinnedBuzz());
        model.addAttribute("buzzList", buzzService.getLatestBuzz());

        AuthUser authUser = sessionHelper.getAuthUserFromSession();
        if (authUser.isAdmin()) {
            UserHelper userHelper = new UserHelper();

        }

        return USER_DASHBOARD_VIEW;
    }

    @GetMapping("/user/inactivate/{userId}")
    public String inactivateUser(@PathVariable int userId) {
        userService.inactivate(userId);
        return "redirect:/profile/search";
    }

    @GetMapping("/user/activate/{userId}")
    public String activateUser(@PathVariable int userId) {
        userService.activate(userId);
        return "redirect:/profile/search";
    }
}
