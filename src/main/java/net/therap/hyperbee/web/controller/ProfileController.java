package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Profile;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.ProfileService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static net.therap.hyperbee.utils.constant.Url.*;

/**
 * @author duity
 * @since 11/22/16.
 */
@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @GetMapping(value = PROFILE_URL)
    public String getProfile(Model model, HttpSession session) {
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");
        int id = authUser.getId();
        User user = userService.findById(id);
        if (user.getProfile() == null) {
            model.addAttribute("profile", new Profile());
        } else {
            Profile profile = user.getProfile();
            model.addAttribute("profile", profile);
        }

        return CREATE_PROFILE_URL;
    }

    @PostMapping(value = PROFILE_URL)
    public String postProfile(@ModelAttribute Profile profile, Model model, HttpSession session) {
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");
        int userId = authUser.getId();
        String message = profileService.saveProfileForUser(profile, userId);
        model.addAttribute("message", message);

        return CREATE_PROFILE_URL;
    }

    @GetMapping(value = USER_PROFILE_URL)
    public String getViewProfile(HttpSession session, Model model) {
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");
        String username = authUser.getUsername();
        User user = userService.findByUsername(username);
        Profile profile = user.getProfile();
        model.addAttribute("profile", profile);

        return VIEW_PROFILE_URL;
    }

    @PostMapping(value = USER_PROFILE_URL)
    public String viewProfile(HttpSession session, Model model) {
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");
        String username = authUser.getUsername();
        User user = userService.findByUsername(username);
        Profile profile = user.getProfile();
        model.addAttribute("profile", profile);

        return VIEW_PROFILE_URL;
    }

    @GetMapping(value = SEARCH_URL)
    public String searchProfilePage() {
        return PROFILE_SEARCH_URL;
    }

    @PostMapping(value = SEARCH_URL)
    public String searchProfile(@RequestParam("search") String username, Model model) {
        User user = userService.findByUsername(username);

        if (user == null) {
            model.addAttribute("message", "No user Found with This username.");
        } else {
            Profile profile = user.getProfile();
            model.addAttribute("profile", profile);
            model.addAttribute("user", user);
        }

        return PROFILE_SEARCH_URL;
    }

    @GetMapping(value = STALK_PROFILE_URL)
    public String stalkProfile(Model model, @PathVariable String username) {
        User user = userService.findByUsername(username);
        Profile profile = user.getProfile();
        model.addAttribute("profile", profile);
        model.addAttribute("user", user);

        return PROFILE_STALK_URL;
    }
}
