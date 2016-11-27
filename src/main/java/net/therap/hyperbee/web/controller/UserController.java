package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.helper.SignUpUserHelper;
import net.therap.hyperbee.web.validator.LoginValidator;
import net.therap.hyperbee.web.validator.SignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private SignUpValidator signUpValidator;

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

        return "user/login";
    }

    @PostMapping(LOGIN_URL)
    public String loginUser(@Validated @ModelAttribute("login") User user, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, HttpSession session) {
        if (bindingResult.hasErrors()) {

            return "user/login";
        }

        User retrievedUser = userService.findByUsernameAndPassword(user);

        if (retrievedUser != null) {
            sessionHelper.persistInSession(retrievedUser, session);

            return "redirect:" + USER_DASHBOARD_URL;
        }

        return "user/login";
    }

    @GetMapping(SIGN_UP_URL)
    public String signUp(Model model) {
        model.addAttribute("signUp", new SignUpUserHelper());

        return "user/signUp";
    }

    @PostMapping(SIGN_UP_URL)
    public String signUpDash(@Validated @ModelAttribute("signUp") SignUpUserHelper signUpUserHelper, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {

            return "user/signUp";
        }

        User user = new User();
        user.setFirstName(signUpUserHelper.getFirstName());
        user.setLastName(signUpUserHelper.getLastName());
        user.setUsername(signUpUserHelper.getUsername());
        user.setEmail(signUpUserHelper.getEmail());
        user.setPassword(signUpUserHelper.getPassword1());

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
        if(!model.containsAttribute("newBuzz")) {
            model.addAttribute("newBuzz", new Buzz());
        }

        model.addAttribute("buzzList", buzzService.getLatestBuzz());

        return "dashboard";
    }
}
