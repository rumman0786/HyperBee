package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:59 AM
 */

@Controller
public class UserController {

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

    @GetMapping("/user/dashboard")
    public String welcome() {
        return "dashboard";
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

    @GetMapping("/user/create")
    public String createUserPage(Model model) {
        model.addAttribute("user", new User());
        return "createUserPage";
    }

    @GetMapping("/user/find")
    public String findUser(Model model) {
        model.addAttribute("user", new User());
        return "findUserPage";
    }

    @PostMapping("/user/create")
    public String createUser(User user) {
        userService.createUser(user);
        return "dashboard";
    }

    @GetMapping("/user/all")
    public String readUsers(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "findUserPage";
    }
}
