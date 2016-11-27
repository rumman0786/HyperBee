package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
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

    @Autowired
    private SessionHelper sessionHelper;

    @GetMapping("/buzz/buzzList")
    public void viewLatestBuzz(Model model) {
        model.addAttribute("buzzList", buzzService.getLatestBuzz());
    }

    @PostMapping("/buzz/sendBuzz")
    public String sendBuzz(@ModelAttribute Buzz newBuzz, HttpSession session, Model model) {
        AuthUser authUser = sessionHelper.retrieveAuthUserFromSession(session);

        newBuzz.setUser(userService.findByUsername(authUser.getUsername()));
        buzzService.saveBuzz(newBuzz);

        model.addAttribute("newBuzz", new Buzz());

        return "redirect:/user/dashboard";
    }

    @GetMapping("/buzz/flagBuzz")
    public String flagBuzz() {
        List<Buzz> buzzList = buzzService.getLatestBuzz();
        buzzService.flagBuzz(buzzList.get(0));

        return "redirect:/user/dashboard";
    }

    @GetMapping("/buzz/deactivateBuzz")
    public String deactivateBuzz() {
        List<Buzz> buzzList = buzzService.getLatestBuzz();
        buzzService.deactivateBuzz(buzzList.get(0));

        return "redirect:/user/dashboard";
    }
}