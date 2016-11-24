package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
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

    @GetMapping("/buzz")
    public String sendBuzzForm(Model model) {
        model.addAttribute("buzzList", buzzService.retrieveLatestBuzz());
        model.addAttribute("newBuzz", new Buzz());
        return "buzz";
    }

    @PostMapping("/buzz")
    public String sendBuzz(@ModelAttribute Buzz newBuzz, HttpServletRequest request, Model model) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");

        newBuzz.setUser(userService.findByUsername(authUser.getUsername()));
        buzzService.createBuzz(newBuzz);


        model.addAttribute("newBuzz", new Buzz());
        model.addAttribute("buzzList", buzzService.retrieveLatestBuzz());
        return "redirect:/buzz";
    }
}