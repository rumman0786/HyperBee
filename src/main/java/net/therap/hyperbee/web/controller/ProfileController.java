package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Profile;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.ProfileService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.utils.Utils;
import net.therap.hyperbee.web.helper.ImageUploader;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.security.AuthUser;
import net.therap.hyperbee.web.validator.ProfileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static net.therap.hyperbee.utils.constant.Constant.*;
import static net.therap.hyperbee.utils.constant.Constant.USER_ATTRIBUTE;
import static net.therap.hyperbee.utils.constant.Messages.*;
import static net.therap.hyperbee.utils.constant.Url.DONE_URL;
import static net.therap.hyperbee.utils.constant.Url.PROFILE_URL;

/**
 * @author duity
 * @since 11/22/16.
 */
@Controller
@RequestMapping(value = PROFILE_URL)
public class ProfileController {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    public static final String PROFILE_EDIT_URL = "/edit";
    public static final String CREATE_PROFILE_URL = "profile/createprofile";
    public static final String USER_PROFILE_URL = "/user";
    public static final String VIEW_PROFILE_URL = "profile/viewprofile";
    public static final String PROFILE_IMAGE_URL = "/image/{imagePath}";
    public static final String COVER_IMAGE_URL = "/cover/{coverImage}";
    public static final String SEARCH_URL = "/search";
    public static final String PROFILE_SEARCH_URL = "profile/searchprofile";
    public static final String STALK_PROFILE_URL = "/stalk/{username}";
    public static final String PROFILE_STALK_URL = "profile/stalkprofile";
    public static final String USERLIST_ATTRIBUTE = "userList";

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageUploader imageUploader;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ProfileValidator profileValidator;

    @InitBinder(PROFILE_ATTRIBUTE)
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(profileValidator);
    }

    @GetMapping(value = PROFILE_EDIT_URL)
    public String getProfile(Model model) {
        model.addAttribute("page", "profile");
        int userId = sessionHelper.getAuthUserIdFromSession();
        User user = userService.findById(userId);
        Profile profile = new Profile();

        if (user.getProfile() == null) {
            profileService.saveProfileForUser(profile, userId);
        } else {
            profile = user.getProfile();
        }
        if (!model.containsAttribute(PROFILE_ATTRIBUTE)) {
            model.addAttribute(PROFILE_ATTRIBUTE, profile);
        }
        model.addAttribute(USER_ATTRIBUTE, user);
        activityService.archive(VISIT_EDIT_PROFILE_ACTIVITY);

        log.debug("AuthUser ID:", userId);
        log.debug("AuthUser Name:", user.getUsername());

        return CREATE_PROFILE_URL;
    }

    @PostMapping
    public String postProfile(@ModelAttribute(PROFILE_ATTRIBUTE) @Valid Profile profile,
                              BindingResult result,
                              Model model,
                              @RequestParam MultipartFile file,
                              @RequestParam MultipartFile coverFile,
                              RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + PROFILE_ATTRIBUTE, result)
                    .addFlashAttribute(PROFILE_ATTRIBUTE, profile);

            return Utils.redirectTo(PROFILE_URL + PROFILE_EDIT_URL);
        }
        model.addAttribute("page", "profile");
        int userId = sessionHelper.getAuthUserIdFromSession();
        User user = userService.findById(userId);
        profile = profileService.saveFileForUser(coverFile, file, user, profile);
        String message = profileService.saveProfileForUser(profile, userId);
        model.addAttribute(DONE_PAGE_KEY_HTML_MESSAGE, message);
        model.addAttribute(USER_ATTRIBUTE, user);
        activityService.archive(EDITED_PROFILE_ACTIVITY);

        log.debug("Edited User Profile:", user.getId() + ":" + user.getUsername());

        return CREATE_PROFILE_URL;
    }

    @RequestMapping(value = USER_PROFILE_URL, method = {RequestMethod.POST, RequestMethod.GET})
    public String getViewProfile(Model model) {
        model.addAttribute("page", "profile");
        AuthUser authUser = sessionHelper.getAuthUserFromSession();
        String username = authUser.getUsername();
        User user = userService.findByUsername(username);
        Profile profile = user.getProfile();

        if (!model.containsAttribute(PROFILE_ATTRIBUTE)) {
            model.addAttribute(PROFILE_ATTRIBUTE, profile);
        }
        model.addAttribute(USER_ATTRIBUTE, user);
        activityService.archive(VISITED_PROFILE_ACTIVITY);

        return VIEW_PROFILE_URL;
    }

    @GetMapping(value = SEARCH_URL)
    public String searchProfilePage(Model model) {
        model.addAttribute("page", "stalk");
        List<User> userList;
        userList = (sessionHelper.getAuthUserFromSession().isAdmin())
                ? userService.findAll()
                : userService.findActiveUsers();
        model.addAttribute(USERLIST_ATTRIBUTE, userList);
        activityService.archive(VISIT_STALKUSER_ACTIVITY);

        return PROFILE_SEARCH_URL;
    }

    @PostMapping(value = SEARCH_URL)
    public String searchProfile(@RequestParam("search") String username, Model model) {
        model.addAttribute("page", "stalk");
        AuthUser authUser = sessionHelper.getAuthUserFromSession();
        User user = userService.findByUsername(username);
        List<User> userList;

        if (authUser.isAdmin()) {
            userList = userService.findAll();
            if (user == null) {
                model.addAttribute(DONE_PAGE_KEY_HTML_MESSAGE, NO_USER_FOUND);
            } else {
                Profile profile = user.getProfile();
                model.addAttribute(PROFILE_ATTRIBUTE, profile);
                model.addAttribute(USER_ATTRIBUTE, user);
            }
        } else {
            userList = userService.findActiveUsers();
            if (user == null || user.getDisplayStatus() == DisplayStatus.INACTIVE) {
                model.addAttribute(DONE_PAGE_KEY_HTML_MESSAGE, NO_USER_FOUND);
            } else {
                Profile profile = user.getProfile();
                model.addAttribute(PROFILE_ATTRIBUTE, profile);
                model.addAttribute(USER_ATTRIBUTE, user);
            }
        }
        model.addAttribute(USERLIST_ATTRIBUTE, userList);
        activityService.archive(STALK_PROFILE_ACTIVITY);

        return PROFILE_SEARCH_URL;
    }

    @GetMapping(value = STALK_PROFILE_URL)
    public String stalkProfile(Model model, @PathVariable String username, RedirectAttributes redirectAttributes) {
        model.addAttribute("page", "stalk");
        User user = userService.findByUsername(username);

        if (user == null) {
            redirectAttributes.addFlashAttribute(DONE_PAGE_KEY_HTML_MESSAGE, NO_USER_FOUND);
            redirectAttributes.addFlashAttribute("messageStyle", "alert alert-success");
            return Utils.redirectTo(DONE_URL);
        }
        Profile profile = user.getProfile();
        model.addAttribute(PROFILE_ATTRIBUTE, profile);
        model.addAttribute(USER_ATTRIBUTE, user);
        activityService.archive(STALK_USER_PROFILE_ACTIVITY);

        return PROFILE_STALK_URL;
    }

    @RequestMapping(value = PROFILE_IMAGE_URL)
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imagePath") String imageName) throws IOException {
        imageUploader.createImagesDirIfNeeded();
        File serverFile = new File(imageUploader.getImagesDirAbsolutePath() + imageName + ".png");

        return Files.readAllBytes(serverFile.toPath());
    }

    @RequestMapping(value = COVER_IMAGE_URL)
    @ResponseBody
    public byte[] getCoverImage(@PathVariable(value = "coverImage") String imageName) throws IOException {
        imageUploader.createImagesDirIfNeeded();
        File serverFile = new File(imageUploader.getImagesDirAbsolutePath() + imageName + ".png");

        return Files.readAllBytes(serverFile.toPath());
    }
}
