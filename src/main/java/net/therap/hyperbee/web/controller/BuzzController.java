package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.utils.Utils;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.security.AuthUser;
import net.therap.hyperbee.web.validator.BuzzValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import static net.therap.hyperbee.utils.constant.Url.BUZZ_BASE_URL;
import static net.therap.hyperbee.utils.constant.Url.USER_DASHBOARD_URL;

/**
 * @author zoha
 * @since 11/22/16
 */
@RequestMapping(BUZZ_BASE_URL)
@Controller

public class BuzzController {

    private static final String BUZZ_VIEW_URL = "/buzzList";
    private static final String BUZZ_CREATE_URL = "/sendBuzz";
    private static final String BUZZ_FLAG_URL = "/flagBuzz";
    private static final String BUZZ_DEACTIVATE_URL = "/deactivateBuzz";
    private static final String BUZZ_PIN_URL = "/pinBuzz";
    private static final String BUZZ_HISTORY_URL = "/buzzHistory";

    private static final Logger log = LogManager.getLogger(BuzzController.class);

    @Autowired
    private BuzzService buzzService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private BuzzValidator buzzValidator;

    @Autowired
    private Utils utils;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(buzzValidator);
    }

    @GetMapping(BUZZ_VIEW_URL)
    public void viewLatestBuzz(Model model) {
        model.addAttribute("pinnedBuzzList", buzzService.getLatestPinnedBuzz());
        model.addAttribute("buzzList", buzzService.getLatestBuzz());

        log.debug("Passing Buzz Lists to view for displaying.");
    }

    @PostMapping(BUZZ_CREATE_URL)
    public String sendBuzz(@ModelAttribute("newBuzz") @Validated Buzz newBuzz, BindingResult result,
                           RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        if (result.hasErrors()) {
            log.debug("Encountered an error. Propagating message to view.");

            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "newBuzz", result);
            redirectAttributes.addFlashAttribute("newBuzz", newBuzz);

            return utils.redirectTo(USER_DASHBOARD_URL);
        }

        AuthUser authUser = sessionHelper.getAuthUserFromSession();

        newBuzz.setUser(userService.findByUsername(authUser.getUsername()));
        buzzService.saveBuzz(newBuzz);

        log.debug("Created new buzz and logged in activity log.");

        model.addAttribute("newBuzz", new Buzz());

        return utils.redirectTo(USER_DASHBOARD_URL);
    }

    @GetMapping(BUZZ_FLAG_URL)
    public String flagBuzz(int id) {
        Buzz flagBuzz = buzzService.flagBuzz(buzzService.getBuzzById(id));
        log.debug("Flagged buzz with id = {} and logged in activity log.", id);

        return utils.redirectTo(USER_DASHBOARD_URL);
    }

    @GetMapping(BUZZ_DEACTIVATE_URL)
    public String deactivateBuzz(int id) {
        Buzz deactivateBuzz = buzzService.deactivateBuzz(buzzService.getBuzzById(id));
        log.debug("Deactivated buzz with id = {} and logged in activity log.", id);

        return utils.redirectTo(USER_DASHBOARD_URL);
    }

    @GetMapping(BUZZ_PIN_URL)
    public String pinBuzz(int id) {
        Buzz pinBuzz = buzzService.pinBuzz(buzzService.getBuzzById(id));
        log.debug("Pinned buzz with id = {} and logged in activity log.", id);

        return utils.redirectTo(USER_DASHBOARD_URL);
    }

    @GetMapping(BUZZ_HISTORY_URL)
    public String viewBuzzHistory(@RequestParam("prev") int prev, @RequestParam("next") int next, Model model) {
        List<Buzz> buzzList = buzzService.getAllBuzz();

        if (next > buzzList.size()) {
            model.addAttribute("buzzList", buzzList.subList(prev, buzzList.size()));
            next = buzzList.size();
        } else {
            model.addAttribute("buzzList", buzzList.subList(prev, next));
        }

        model.addAttribute("prev", prev);
        model.addAttribute("next", next);

        model.addAttribute("page", "buzz");

        log.debug("Sending buzz list as per requirement for viewing history.");

        return BUZZ_BASE_URL + BUZZ_HISTORY_URL;
    }
}