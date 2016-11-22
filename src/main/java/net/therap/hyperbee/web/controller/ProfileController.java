package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.dao.ProfileDao;
import net.therap.hyperbee.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author duity
 * @since 11/22/16.
 */
@Controller
public class ProfileController {

    @Autowired
    private ProfileDao profileDao;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(Model model){
        model.addAttribute("profile", new Profile());

        return "profile/createprofile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String postProfile(@ModelAttribute Profile profile){
        profileDao.save(profile);

        return "profile/createprofile";
    }
}
