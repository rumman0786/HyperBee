package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.RoleType;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Scanner;

/**
 * @author rayed
 * @since 11/22/16 10:59 AM
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private static Scanner scanner = new Scanner(System.in);

    @GetMapping("/")
    public String entrance() {
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/user/login")
    public String loginUser(User user, HttpSession session) {
        System.out.println(user);

        User retrievedUser;
        retrievedUser = userService.findByUsernameAndPassword(user);

        System.out.println(retrievedUser);

        if (retrievedUser != null) {
            AuthUser authUser = new AuthUser();
            authUser.setUsername(user.getUsername());
            authUser.setUserRole(RoleType.USER);

            session.setAttribute("authUser", authUser);

            return "redirect:/user/welcome";
        }

        return "redirect:/";
    }

    @GetMapping("/user/welcome")
    public String welcome() {
        return "welcome";
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
        return "welcome";
    }

    @GetMapping("/user/all")
    public String readUsers(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "findUserPage";
    }

    @GetMapping("/user/findByUsername")
    public String readUserByName() {
        String name = scanner.nextLine();
        User user = userService.findByUsername(name);
        return "welcome";
    }
}
