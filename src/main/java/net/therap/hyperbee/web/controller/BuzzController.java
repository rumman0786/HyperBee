package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.service.UserServiceImpl;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        model.addAttribute("buzzList", buzzService.getLatestBuzz());
        model.addAttribute("newBuzz", new Buzz());
        return "buzz";
    }

    @PostMapping("/buzz")
    public String sendBuzz(@ModelAttribute Buzz newBuzz, HttpServletRequest request, Model model) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");

        newBuzz.setUser(userService.findByUsername(authUser.getUsername()));
        buzzService.saveBuzz(newBuzz);


        model.addAttribute("newBuzz", new Buzz());
        model.addAttribute("buzzList", buzzService.getLatestBuzz());
        return "redirect:/buzz";
    }

    @GetMapping("/buzz/flagBuzz")
    public String flagBuzz() {
        List<Buzz> buzzList = buzzService.getLatestBuzz();
        buzzService.flagBuzz(buzzList.get(0));
        return "redirect:/buzz";
    }

    @GetMapping("/buzz/deactivateBuzz")
    public String deactivateBuzz() {
        List<Buzz> buzzList = buzzService.getLatestBuzz();
        buzzService.deactivateBuzz(buzzList.get(0));
        return "redirect:/buzz";
    }
}