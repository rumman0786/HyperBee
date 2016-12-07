package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.utils.Utils;
import net.therap.hyperbee.web.helper.BuzzHelper;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.security.AuthUser;
import net.therap.hyperbee.web.validator.BuzzValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    //URL Constants
    private static final String BUZZ_VIEW_URL = "/buzzList";
    private static final String BUZZ_SELECT_VIEW_URL = "/buzzList/{type}";
    private static final String BUZZ_CREATE_URL = "/sendBuzz";
    private static final String BUZZ_FLAG_URL = "/flagBuzz";
    private static final String BUZZ_DEACTIVATE_URL = "/deactivateBuzz";
    private static final String BUZZ_PIN_URL = "/pinBuzz";
    private static final String BUZZ_HISTORY_URL = "/buzzHistory";
    private static final String BUZZ_LIST_URL = "/buzzListByType";

    // Log Message Constants
    private static final String BUZZ_GET_LOG = "Passing Buzz Lists to view for displaying.";
    private static final String BUZZ_SAVE_LOG = "Created new buzz and logged in activity log.";
    private static final String BUZZ_FLAG_LOG = "Flagged buzz with id = {} and logged in activity log.";
    private static final String BUZZ_REMOVE_LOG = "Deactivated buzz with id = {} and logged in activity log.";
    private static final String BUZZ_PIN_LOG = "Pinned buzz with id = {} and logged in activity log.";
    private static final String BUZZ_HISTORY_LOG = "Sending buzz list as per requirement for buzz history.";
    private static final String BUZZ_TODAY_LOG = "Sending {} buzz list as per requirement of sidebar counter.";
    private static final String BUZZ_ERROR_LOG = "Encountered an error. Propagating message to view.";

    // Attribute alias and value Constants
    private static final String BUZZ_LIST_ATTR_NAME = "buzzList";
    private static final String BUZZ_LIST_SIZE_ATTR_NAME = "buzzListSize";
    private static final String BUZZ_PINNED_LIST_ATTR_NAME = "pinnedBuzzList";
    private static final String BUZZ_NEW_ATTR_NAME = "newBuzz";
    private static final String BUZZ_PAGE_ATTR_NAME = "page";
    private static final String BUZZ_PAGE_ATTR_VALUE = "buzz";
    private static final String BUZZ_NEXT_ATTR_NAME = "next";
    private static final String BUZZ_PREV_ATTR_NAME = "prev";
    private static final String BUZZ_TYPE_ATTR_NAME = "type";

    private static final Logger log = LogManager.getLogger(BuzzController.class);

    @Autowired
    private BuzzService buzzService;

    @Autowired
    private UserService userService;

    @Autowired
    private BuzzHelper buzzHelper;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private BuzzValidator buzzValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(buzzValidator);
    }

    @GetMapping(BUZZ_VIEW_URL)
    public void viewLatestBuzz(Model model) {
        model.addAttribute(BUZZ_PINNED_LIST_ATTR_NAME, buzzService.getLatestPinnedBuzz());
        model.addAttribute(BUZZ_LIST_ATTR_NAME, buzzService.getLatestBuzz());

        log.debug(BUZZ_GET_LOG);
    }

    @GetMapping(BUZZ_SELECT_VIEW_URL)
    public String viewBuzzByType(@PathVariable(BUZZ_TYPE_ATTR_NAME) String type, Model model) {
        if (sessionHelper.getAuthUserFromSession().isAdmin()) {
            model.addAttribute(BUZZ_LIST_ATTR_NAME, buzzHelper.getListByType(type));
        } else {
            model.addAttribute(BUZZ_LIST_ATTR_NAME, buzzHelper.getListByTypeAndUser(type,
                    sessionHelper.getUserIdFromSession()));
        }

        log.debug(BUZZ_TODAY_LOG, type);

        model.addAttribute(BUZZ_TYPE_ATTR_NAME, Utils.convertToSentenceCase(type));

        return BUZZ_BASE_URL + BUZZ_LIST_URL;
    }

    @PostMapping(BUZZ_CREATE_URL)
    public String sendBuzz(@ModelAttribute(BUZZ_NEW_ATTR_NAME) @Valid Buzz newBuzz, BindingResult result,
                           RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        if (result.hasErrors()) {
            log.debug(BUZZ_ERROR_LOG);

            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + BUZZ_NEW_ATTR_NAME, result);
            redirectAttributes.addFlashAttribute(BUZZ_NEW_ATTR_NAME, newBuzz);

            return Utils.redirectTo(USER_DASHBOARD_URL);
        }

        AuthUser authUser = sessionHelper.getAuthUserFromSession();

        newBuzz.setUser(userService.findByUsername(authUser.getUsername()));
        buzzService.saveBuzz(newBuzz);

        log.debug(BUZZ_SAVE_LOG);

        model.addAttribute(BUZZ_NEW_ATTR_NAME, new Buzz());

        return Utils.redirectTo(USER_DASHBOARD_URL);
    }

    @GetMapping(BUZZ_FLAG_URL)
    public String flagBuzz(int id) {
        Buzz flagBuzz = buzzService.flagBuzz(buzzService.getBuzzById(id));
        log.debug(BUZZ_FLAG_LOG, id);

        return Utils.redirectTo(USER_DASHBOARD_URL);
    }

    @GetMapping(BUZZ_DEACTIVATE_URL)
    public String deactivateBuzz(int id) {
        Buzz deactivateBuzz = buzzService.deactivateBuzz(buzzService.getBuzzById(id));
        log.debug(BUZZ_REMOVE_LOG, id);

        return Utils.redirectTo(USER_DASHBOARD_URL);
    }

    @GetMapping(BUZZ_PIN_URL)
    public String pinBuzz(int id) {
        Buzz pinBuzz = buzzService.pinBuzz(buzzService.getBuzzById(id));
        log.debug(BUZZ_PIN_LOG, id);

        return Utils.redirectTo(USER_DASHBOARD_URL);
    }

    @GetMapping(BUZZ_HISTORY_URL)
    public String viewBuzzHistory(@RequestParam(BUZZ_PREV_ATTR_NAME) int prev,
                                  @RequestParam(BUZZ_NEXT_ATTR_NAME) int next, Model model) {

        List<Buzz> buzzList = buzzService.getAllBuzz();
        model.addAttribute(BUZZ_LIST_SIZE_ATTR_NAME, buzzList.size());

        if (next > buzzList.size()) {
            model.addAttribute(BUZZ_LIST_ATTR_NAME, buzzList.subList(prev, buzzList.size()));
            next = buzzList.size();
        } else {
            model.addAttribute(BUZZ_LIST_ATTR_NAME, buzzList.subList(prev, next));
        }

        model.addAttribute(BUZZ_PREV_ATTR_NAME, prev);
        model.addAttribute(BUZZ_NEXT_ATTR_NAME, next);

        model.addAttribute(BUZZ_PAGE_ATTR_NAME, BUZZ_PAGE_ATTR_VALUE);

        log.debug(BUZZ_HISTORY_LOG);

        return BUZZ_BASE_URL + BUZZ_HISTORY_URL;
    }
}