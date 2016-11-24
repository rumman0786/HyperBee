package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.HiveService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.command.UserIdInfo;
import net.therap.hyperbee.web.helper.UploadedFile;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewHive(ModelMap model, HttpSession session) {
        int userId = getUserIdFromSession(session);
        model.put("hiveList", hiveService.getHiveListByUserId(userId));

        return "hive/hive";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createHive(Model model) {

        model.addAttribute("hive", new Hive());

        return "hive/hiveForm";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String viewHivePage(Model model,@PathVariable("id") int id) {
        model.addAttribute("hiveId", id);
        model.addAttribute("hive", hiveService.retrieveHiveById(id));
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("userIdInfo", new UserIdInfo());

        return "hive/showHive";
    }

    @RequestMapping(value = "/insertuser/{hiveId}", method = RequestMethod.POST)
    public String addUserToHive(@ModelAttribute UserIdInfo userIdInfo, Model model, @PathVariable("hiveId") int hiveId) {

        model.addAttribute("userInfoId", userIdInfo);
        hiveService.insertUsersToHive(hiveId, userIdInfo.getUserIdList());

        return "redirect:/hive/show/"+hiveId;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveHiveForm(@ModelAttribute Hive hive, @RequestParam CommonsMultipartFile fileUpload, Model model, HttpSession session) throws IOException {
        model.addAttribute("hiveName", hive.getName());

        int userId = getUserIdFromSession(session);
        List<User> userList = new ArrayList<User>();
        userList.add(userService.findById(userId));
        hive.setUserList(userList);

        String filename = uploadedFile.uploadFile(fileUpload, hive.getName());
        hive.setImagePath(filename);
        hiveService.insertHive(hive);

        return "hive/showHive";
    }

    private int getUserIdFromSession(HttpSession session) {
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");
        return authUser.getId();
    }

}