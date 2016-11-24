package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.dao.ProfileDao;
import net.therap.hyperbee.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static net.therap.hyperbee.utils.constant.Url.PROFILE_URL;
import static net.therap.hyperbee.utils.constant.Url.CREATE_PROFILE_URL;

/**
 * @author duity
 * @since 11/22/16.
 */
@Controller
@RequestMapping(value = PROFILE_URL)
public class ProfileController {

    @Autowired
    private ProfileDao profileDao;

    @GetMapping
    public String getProfile(Model model){
        model.addAttribute("profile", new Profile());

        return CREATE_PROFILE_URL;
    }

    @PostMapping
    public String postProfile(@ModelAttribute Profile profile, Model model){
        String message=profileDao.save(profile);
        model.addAttribute("message", message);

        return CREATE_PROFILE_URL;
    }
}
