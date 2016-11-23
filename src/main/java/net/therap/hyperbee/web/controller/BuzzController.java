package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zoha
 * @since 11/22/16
 */

@Controller
public class BuzzController {

    @Autowired
    BuzzService buzzService;

    @Autowired
    UserService userService;

    @GetMapping("/sendBuzz")
    public String sendBuzzForm(Model model) {
        model.addAttribute("newBuzz", new Buzz());
        return "sendBuzz";
    }

    @PostMapping("/sendBuzz")
    public String sendBuzz(@ModelAttribute Buzz newBuzz) {
        newBuzz.setUser(userService.findById(1));
        buzzService.createBuzz(newBuzz);

        return "welcome";
    }

    @GetMapping("/readBuzz")
    public String readBuzz(Model model) {
        model.addAttribute("buzzList", buzzService.retrieveLatestBuzz());
        return "readBuzz";
    }
}
