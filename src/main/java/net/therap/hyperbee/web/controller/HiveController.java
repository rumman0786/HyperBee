package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.service.HiveService;
import net.therap.hyperbee.web.helper.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String showHiveForm(ModelMap model) {
        model.put("hive", new Hive());
        model.addAttribute("name", new String());
        model.addAttribute("description", new String());
        model.addAttribute("imagePath", new String());

       return HIVE;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String saveHiveForm(@ModelAttribute Hive hive, @RequestParam CommonsMultipartFile fileUpload, Model model) throws IOException {

        String filename = uploadedFile.uploadFile(fileUpload,hive.getName());

        hive.setImagePath(filename);
        model.addAttribute("hive", hive);
        model.addAttribute("name", hive.getName());
        model.addAttribute("description", hive.getDescription());
        model.addAttribute("imagePath", System.getProperty("catalina.home") + "/webapps/upload/" + filename);

        hiveService.insertHive(hive);

        return "hive/show";
    }


}