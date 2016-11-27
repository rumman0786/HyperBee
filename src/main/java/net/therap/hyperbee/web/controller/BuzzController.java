package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.security.AuthUser;
import net.therap.hyperbee.web.validator.BuzzValidator;
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

import javax.naming.Binding;
import javax.servlet.http.HttpSession;

import static net.therap.hyperbee.utils.constant.Url.USER_DASHBOARD_URL;

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

    @Autowired
    private BuzzValidator buzzValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(buzzValidator);
    }

    @GetMapping("/buzz/buzzList")
    public void viewLatestBuzz(Model model) {
        model.addAttribute("buzzList", buzzService.getLatestBuzz());
    }

    @PostMapping("/buzz/sendBuzz")
    public String sendBuzz(@ModelAttribute("newBuzz") @Validated Buzz newBuzz, BindingResult result,
                           RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "newBuzz", result);
            redirectAttributes.addFlashAttribute("newBuzz", newBuzz);
            return "redirect:" + USER_DASHBOARD_URL;
        }

        AuthUser authUser = sessionHelper.retrieveAuthUserFromSession(session);

        newBuzz.setUser(userService.findByUsername(authUser.getUsername()));
        buzzService.saveBuzz(newBuzz);

        model.addAttribute("newBuzz", new Buzz());

        return "redirect:" + USER_DASHBOARD_URL;
    }

    @GetMapping("/buzz/flagBuzz")
    public String flagBuzz(int id) {
        buzzService.flagBuzz(buzzService.getBuzzById(id));

        return "redirect:/user/dashboard";
    }

    @GetMapping("/buzz/deactivateBuzz")
    public String deactivateBuzz(int id) {
        buzzService.deactivateBuzz(buzzService.getBuzzById(id));

        return "redirect:/user/dashboard";
    }
}