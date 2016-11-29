package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.Post;
import net.therap.hyperbee.service.HiveService;
import net.therap.hyperbee.service.PostService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.command.UserIdInfo;
import net.therap.hyperbee.web.helper.ImageUploader;
import net.therap.hyperbee.web.helper.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * @author azim
 * @since 11/22/16
 */
@Controller
@RequestMapping("/user/hive")
public class HiveController {

    private String SHOW_HIVE = "hive/showHive";

    private String HIVE = "hive/hive";

    @Autowired
    private HiveService hiveService;

    @Autowired
    private PostService postService;

    @Autowired
    ImageUploader imageUploader;

    @Autowired
    UserService userService;

    @Autowired
    SessionHelper sessionHelper;

    @RequestMapping(method = RequestMethod.GET)
    public String viewHive(ModelMap model) {
        int userId = sessionHelper.getUserIdFromSession();
        model.put("hiveList", hiveService.getHiveListByUserId(userId));
        model.addAttribute("hive", new Hive());
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("userIdInfo", new UserIdInfo());

        return HIVE;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String viewHivePage(Model model, @PathVariable("id") int id) {
        model.addAttribute("hiveId", id);
        model.addAttribute("hive", hiveService.retrieveHiveById(id));
        model.addAttribute("userList", hiveService.getUserNotInList(id));
        model.addAttribute("enlistedUser", hiveService.getUserInList(id));
        model.addAttribute("userIdInfo", new UserIdInfo());
        model.addAttribute("post", new Post());
        model.addAttribute("postList", postService.getPostListByHive(id));

        if (hiveService.retrieveHiveById(id).getNoticeList().isEmpty()) {
            model.addAttribute("noticeList", new ArrayList<Notice>());
        } else {
            model.addAttribute("noticeList", hiveService.getNoticeList(id));
        }

        return SHOW_HIVE;
    }

    @RequestMapping(value = "/insertuser/{hiveId}", method = RequestMethod.POST)
    public String addUserToHive(@ModelAttribute UserIdInfo userIdInfo, Model model, @PathVariable("hiveId") int hiveId) {
        model.addAttribute("userInfoId", userIdInfo);
        hiveService.insertUsersToHive(hiveId, userIdInfo.getUserIdList());

        return "redirect:/user/hive/show/" + hiveId;
    }

    @RequestMapping(value = "/removeuser/{hiveId}", method = RequestMethod.POST)
    public String RemoveUserFromHive(@ModelAttribute UserIdInfo userIdInfo, Model model, @PathVariable("hiveId") int hiveId) {
        model.addAttribute("userInfoId", userIdInfo);
        hiveService.removeUsersFromHive(hiveId, userIdInfo.getUserIdList());

        return "redirect:/user/hive/show/" + hiveId;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveHiveForm(@ModelAttribute Hive hive, @RequestParam MultipartFile file, Model model) throws IOException {
        model.addAttribute("hiveName", hive.getName());

        String filename = hive.getName() + file.getOriginalFilename();
        hive.setImagePath(filename);

        if (file.isEmpty()) {
        } else {
            imageUploader.createImagesDirIfNeeded();
            model.addAttribute("message", imageUploader.createImage(filename, file));
        }


        int userId = sessionHelper.getUserIdFromSession();

        Hive newHive = hiveService.insertFirstUserToHive(hive, userId);
        hiveService.insertHive(newHive);
        int hiveId = hiveService.getHiveIdByHiveName(newHive.getName());

        return "redirect:/user/hive/show/" + hiveId;
    }

    @RequestMapping(value = "/post/{hiveId}", method = RequestMethod.POST)
    public String savePost(@ModelAttribute Post post, Model model, @PathVariable("hiveId") int hiveId) {

        int userId = sessionHelper.getUserIdFromSession();
        postService.savePost(userId, hiveId, post);

        return "redirect:/user/hive/show/" + hiveId;
    }

    @RequestMapping(value = "/image/{imagePath}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imagePath") String imageName) throws IOException {
        imageUploader.createImagesDirIfNeeded();
        System.out.println(imageName);
        File serverFile = new File(imageUploader.getImagesDirAbsolutePath() + imageName + ".png");

        return Files.readAllBytes(serverFile.toPath());
    }
}