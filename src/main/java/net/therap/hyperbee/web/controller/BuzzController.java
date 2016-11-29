package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.utils.constant.Messages;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.security.AuthUser;
import net.therap.hyperbee.web.validator.BuzzValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import java.util.List;

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

    @Autowired
    ActivityService activityService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(buzzValidator);
    }

    @GetMapping("/buzz/buzzList")
    public void viewLatestBuzz(Model model) {
        model.addAttribute("pinnedBuzzList", buzzService.getPinnedBuzz());
        model.addAttribute("buzzList", buzzService.getLatestBuzz());
    }

    @PostMapping("/buzz/sendBuzz")
    public String sendBuzz(@ModelAttribute("newBuzz") @Validated Buzz newBuzz, BindingResult result,
                           RedirectAttributes redirectAttributes, HttpSession session, Model model) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "newBuzz", result);
            redirectAttributes.addFlashAttribute("newBuzz", newBuzz);
            return "redirect:" + USER_DASHBOARD_URL;
        }

        AuthUser authUser = sessionHelper.retrieveAuthUserFromSession();

        newBuzz.setUser(userService.findByUsername(authUser.getUsername()));
        buzzService.saveBuzz(newBuzz);
        activityService.archive(Messages.BUZZ_SEND_SUCCESS.replaceAll("<message>", newBuzz.getMessage()));

        model.addAttribute("newBuzz", new Buzz());

        return "redirect:" + USER_DASHBOARD_URL;
    }

    @GetMapping("/buzz/flagBuzz")
    public String flagBuzz(int id) {
        Buzz tempBuzz = buzzService.flagBuzz(buzzService.getBuzzById(id));
        activityService.archive(Messages.BUZZ_FLAG_SUCCESS.replaceAll("<message>", tempBuzz.getMessage()));

        return "redirect:/user/dashboard";
    }

    @GetMapping("/buzz/deactivateBuzz")
    public String deactivateBuzz(int id) {
        Buzz tempBuzz = buzzService.deactivateBuzz(buzzService.getBuzzById(id));
        activityService.archive(Messages.BUZZ_DELETE_SUCCESS.replaceAll("<message>", tempBuzz.getMessage()));

        return "redirect:/user/dashboard";
    }

    @GetMapping("/buzz/pinBuzz")
    public String pinBuzz(int id) {
        Buzz tempBuzz = buzzService.pinBuzz(buzzService.getBuzzById(id));
        activityService.archive(Messages.BUZZ_PINNED_SUCCESS.replaceAll("<message>", tempBuzz.getMessage()));

        return "redirect:/user/dashboard";
    }

    @GetMapping("/buzz/buzzHistory")
    public String viewBuzzHistory(@RequestParam("prev") int prev, @RequestParam("next") int next, Model model) {
        List <Buzz> buzzList = buzzService.getAllBuzz();

        if(next > buzzList.size()) {
            model.addAttribute("buzzList", buzzList.subList(prev, buzzList.size()));
            next = buzzList.size();
        } else {
            model.addAttribute("buzzList", buzzList.subList(prev, next));
        }

        model.addAttribute("prev", prev);
        model.addAttribute("next", next);

        return "/buzz/buzzHistory";
    }
}