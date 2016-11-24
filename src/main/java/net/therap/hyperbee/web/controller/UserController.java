package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.helper.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

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
    private SessionHelper sessionHelper;

    @GetMapping(ROOT_URL)
    public String entry() {
        return "redirect:" + LOGIN_URL;
    }

    @GetMapping(LOGIN_URL)
    public String login(Model model) {
        model.addAttribute("user", new User());

        return "login";
    }

    @PostMapping(LOGIN_URL)
    public String loginUser(User user, HttpSession session) {
        User retrievedUser = userService.findByUsernameAndPassword(user);

        if (retrievedUser != null) {
            sessionHelper.persistInSession(retrievedUser, session);

            return "redirect:" + USER_DASHBOARD_URL;
        }

        return "redirect:" + LOGIN_URL;
    }

    @GetMapping(SIGN_UP_URL)
    public String signup(Model model) {
        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping(SIGN_UP_URL)
    public String signupDash(User user, HttpSession session) {
        User retrievedUser = userService.createUser(user);
        sessionHelper.persistInSession(retrievedUser, session);

        return "redirect:" + USER_DASHBOARD_URL;
    }

    @GetMapping(LOGOUT_URL)
    public String logout(HttpSession session) {
        sessionHelper.invalidateSession(session);

        return "redirect:" + LOGIN_URL;
    }

    @GetMapping(USER_DASHBOARD_URL)
    public String welcome(Model model) {
        model.addAttribute("newBuzz", new Buzz());
        model.addAttribute("buzzList", buzzService.getLatestBuzz());

        return "dashboard";
    }
}
