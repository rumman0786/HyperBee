package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/user/displayCreateUserPage")
    public String createUserPage(Model model) {
        model.addAttribute("user", new User());
        return "createUserPage";
    }

    @PostMapping("/user/createUser")
    public String createUser(User user) {
        userService.createUser(user);
        return "welcome";
    }

    @GetMapping("/user/findAll")
    public String readUsers(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "readUserPage";
    }

    @GetMapping("/user/findUserByName")
    public String readUserByName() {
        String name = scanner.nextLine();
        User user = userService.findByUsername(name);
        return "welcome";
    }
}
