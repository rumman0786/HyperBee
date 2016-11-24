package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @GetMapping("/")
    public String entry() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(User user, HttpSession session) {
        AuthUser authUser;
        authUser = userService.findByUsernameAndPassword(user);

        if (authUser != null) {
            session.setAttribute("authUser", authUser);
            return "redirect:/user/dashboard";
        }

        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupDash(User user) {
        return "redirect:/user/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/login";
    }

    @GetMapping("/user/dashboard")
    public String welcome(Model model) {
        model.addAttribute("newBuzz", new Buzz());
        model.addAttribute("buzzList", buzzService.getLatestBuzz());

        return "dashboard";
    }
}
