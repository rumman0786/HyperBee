package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Profile;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.ProfileService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.helper.ImageUploader;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static net.therap.hyperbee.utils.constant.Url.*;
import static net.therap.hyperbee.utils.constant.DomainConstant.*;
import static net.therap.hyperbee.utils.constant.Messages.*;

/**
 * @author duity
 * @since 11/22/16.
 */
@Controller
@RequestMapping(value = PROFILE_URL)
public class ProfileController {

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageUploader imageUploader;

    @GetMapping(value = PROFILE_EDIT_URL)
    public String getProfile(Model model, HttpSession session) {
        AuthUser authUser = sessionHelper.retrieveAuthUserFromSession();
        int id = authUser.getId();
        User user = userService.findById(id);

        if (user.getProfile() == null) {
            model.addAttribute(PROFILE_ATTRIBUTE, new Profile());
            model.addAttribute(USER_ATTRIBUTE, user);
        } else {
            Profile profile = user.getProfile();
            model.addAttribute(PROFILE_ATTRIBUTE, profile);
            model.addAttribute(USER_ATTRIBUTE, user);
        }

        return CREATE_PROFILE_URL;
    }

    @PostMapping
    public String postProfile(@ModelAttribute Profile profile, Model model, @RequestParam String imagePath,
                              @RequestParam MultipartFile file,
                              @RequestParam String coverImage,
                              @RequestParam MultipartFile coverFile) {
        AuthUser authUser = sessionHelper.retrieveAuthUserFromSession();
        int userId = authUser.getId();

        String message = profileService.saveProfileForUser(profile, userId);
        User user = userService.findById(userId);

        model.addAttribute("message", message);
        model.addAttribute(USER_ATTRIBUTE, user);

        if (!file.isEmpty()) {
            imageUploader.createImagesDirIfNeeded();
            model.addAttribute("message2", imageUploader.createImage(imagePath, file));
        }

        if (!coverFile.isEmpty()) {
            imageUploader.createImagesDirIfNeeded();
            model.addAttribute("message3", imageUploader.createImage(coverImage, coverFile));
        }

        return CREATE_PROFILE_URL;
    }

    @GetMapping(value = USER_PROFILE_URL)
    public String getViewProfile(Model model) {
        AuthUser authUser = sessionHelper.retrieveAuthUserFromSession();
        String username = authUser.getUsername();
        User user = userService.findByUsername(username);
        Profile profile = user.getProfile();

        model.addAttribute(PROFILE_ATTRIBUTE, profile);
        model.addAttribute(USER_ATTRIBUTE, user);

        return VIEW_PROFILE_URL;
    }

    @PostMapping(value = USER_PROFILE_URL)
    public String viewProfile(Model model) {
        AuthUser authUser = sessionHelper.retrieveAuthUserFromSession();
        String username = authUser.getUsername();
        User user = userService.findByUsername(username);
        Profile profile = user.getProfile();

        model.addAttribute(PROFILE_ATTRIBUTE, profile);
        model.addAttribute(USER_ATTRIBUTE, user);

        return VIEW_PROFILE_URL;
    }

    @GetMapping(value = SEARCH_URL)
    public String searchProfilePage(Model model) {
        List<User> userList;
        if (sessionHelper.retrieveAuthUserFromSession().isAdmin()) {
            userList = userService.findAll();
        } else {
            userList = userService.findActiveUsers();
        }
        model.addAttribute("userList", userList);

        return PROFILE_SEARCH_URL;
    }

    @PostMapping(value = SEARCH_URL)
    public String searchProfile(@RequestParam("search") String username, Model model) {
        User user = userService.findByUsername(username);
        DisplayStatus status=user.getDisplayStatus();

        if (user == null || status== DisplayStatus.INACTIVE) {
            model.addAttribute("message", NO_USER_FOUND);
        } else {
            Profile profile = user.getProfile();
            model.addAttribute(PROFILE_ATTRIBUTE, profile);
            model.addAttribute(USER_ATTRIBUTE, user);
        }

        return PROFILE_SEARCH_URL;
    }

    @GetMapping(value = STALK_PROFILE_URL)
    public String stalkProfile(Model model, @PathVariable String username) {
        User user = userService.findByUsername(username);
        Profile profile = user.getProfile();
        model.addAttribute(PROFILE_ATTRIBUTE, profile);
        model.addAttribute(USER_ATTRIBUTE, user);

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
