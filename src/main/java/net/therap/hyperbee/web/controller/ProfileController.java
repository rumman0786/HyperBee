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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static net.therap.hyperbee.utils.constant.Constant.PROFILE_ATTRIBUTE;
import static net.therap.hyperbee.utils.constant.Constant.USER_ATTRIBUTE;
import static net.therap.hyperbee.utils.constant.Messages.*;
import static net.therap.hyperbee.utils.constant.Url.*;

/**
 * @author duity
 * @since 11/22/16.
 */
@Controller
@RequestMapping(value = PROFILE_URL)
public class ProfileController {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

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

    @Autowired
    private Utils utils;

    @InitBinder("profile")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(profileValidator);
    }

    @GetMapping(value = PROFILE_EDIT_URL)
    public String getProfile(Model model) {
        model.addAttribute("page", "profile");
        AuthUser authUser = sessionHelper.getAuthUserFromSession();
        int id = authUser.getId();
        User user = userService.findById(id);
        Profile profile;

        if (user.getProfile() == null) {
            profile = new Profile();
            profileService.saveProfileForUser(profile, authUser.getId());
        } else {
            profile = user.getProfile();
        }
        model.addAttribute(PROFILE_ATTRIBUTE, profile);
        model.addAttribute(USER_ATTRIBUTE, user);
        activityService.archive(VISIT_EDIT_PROFILE_ACTIVITY);

        log.debug("AuthUser ID:", authUser.getId());
        log.debug("AuthUser Name:", authUser.getUsername());

        return CREATE_PROFILE_URL;
    }

    @PostMapping
    public String postProfile(@ModelAttribute("profile") @Validated Profile profile,
                              BindingResult result,
                              Model model,
                              @RequestParam MultipartFile file,
                              @RequestParam MultipartFile coverFile,
                              RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "profile", result)
                    .addFlashAttribute("profile", profile);

            return utils.redirectTo(PROFILE_URL + PROFILE_EDIT_URL);
        }
        model.addAttribute("page", "profile");
        AuthUser authUser = sessionHelper.getAuthUserFromSession();
        int userId = authUser.getId();
        User user = userService.findById(userId);
        profile = profileService.saveFileForUser(coverFile, file, user, profile);
        String message = profileService.saveProfileForUser(profile, userId);
        model.addAttribute("message", message);
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

        if (sessionHelper.getAuthUserFromSession().isAdmin()) {
            userList = userService.findAll();
        } else {
            userList = userService.findActiveUsers();
        }
        model.addAttribute("userList", userList);
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
                model.addAttribute("message", NO_USER_FOUND);
            } else {
                Profile profile = user.getProfile();
                model.addAttribute(PROFILE_ATTRIBUTE, profile);
                model.addAttribute(USER_ATTRIBUTE, user);
            }
        } else {
            userList = userService.findActiveUsers();
            if (user == null || user.getDisplayStatus() == DisplayStatus.INACTIVE) {
                model.addAttribute("message", NO_USER_FOUND);
            } else {
                Profile profile = user.getProfile();
                model.addAttribute(PROFILE_ATTRIBUTE, profile);
                model.addAttribute(USER_ATTRIBUTE, user);
            }
        }
        model.addAttribute("userList", userList);
        activityService.archive(STALK_PROFILE_ACTIVITY);

        return PROFILE_SEARCH_URL;
    }

    @GetMapping(value = STALK_PROFILE_URL)
    public String stalkProfile(Model model, @PathVariable String username, RedirectAttributes redirectAttributes) {
        model.addAttribute("page", "stalk");
        User user = userService.findByUsername(username);

        if (user == null) {
            redirectAttributes.addFlashAttribute("message", NO_USER_FOUND);
            redirectAttributes.addFlashAttribute("messageStyle", "alert alert-success");
            return "redirect:" + DONE_URL;
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
