package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.service.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

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

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String showHiveForm(ModelMap model) {
        model.put("hive", new Hive());
        model.addAttribute("name", new String());
        model.addAttribute("description", new String());
        model.addAttribute("imagePath", new String());

        return HIVE;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String saveHiveForm(@ModelAttribute Hive hive, @RequestParam CommonsMultipartFile fileUpload, Model model) {

        String filename = fileUpload.getOriginalFilename();

        filename = "/home/azim/apache-tomcat-8.5.6/webapps/data/" + hive.getName() + filename;
        try {
            byte barr[] = fileUpload.getBytes();

            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        hive.setImagePath(filename);
        model.addAttribute("hive", hive);
        model.addAttribute("name", hive.getName());
        model.addAttribute("description", hive.getDescription());
        model.addAttribute("imagePath", filename);

        System.out.println("name " + hive.getName());

        System.out.println("description " + hive.getDescription());

        System.out.println("imagePath " + filename);

        hiveService.insertHive(hive);
        return "hive/show";
    }
}
