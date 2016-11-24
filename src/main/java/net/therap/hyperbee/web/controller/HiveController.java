package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.service.HiveService;
import net.therap.hyperbee.web.helper.UploadedFile;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author azim
 * @since 11/22/16
 */
@Controller
@RequestMapping("/hive")
public class HiveController {

    private String HIVE = "hive/create";

    @Autowired
    private HiveService hiveService;

    @Autowired
    UploadedFile uploadedFile;

    @RequestMapping(method = RequestMethod.GET)
    public String viewHive(ModelMap model, HttpSession session) {

        model.put("hiveList", hiveService.retrieveHive());

        return "hive/hive";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createHive(Model model) {

        model.addAttribute("hive", new Hive());

        return "hive/hiveForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveHiveForm(@ModelAttribute Hive hive, @RequestParam CommonsMultipartFile fileUpload, Model model, HttpSession session) throws IOException {

        String filename = uploadedFile.uploadFile(fileUpload, hive.getName());
        hive.setImagePath(filename);
        hiveService.insertHive(hive);

        return "hive/hive";
    }

    private int getUserIdFromSession(HttpSession session) {
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");
        return authUser.getId();
    }

}