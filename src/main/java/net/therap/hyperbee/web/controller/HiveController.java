package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Post;
import net.therap.hyperbee.service.HiveService;
import net.therap.hyperbee.service.PostService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.command.UserIdInfo;
import net.therap.hyperbee.web.helper.ImageUploader;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.validator.HiveValidator;
import net.therap.hyperbee.web.validator.PostValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static net.therap.hyperbee.utils.Utils.redirectTo;

/**
 * @author azim
 * @since 11/22/16
 */
@Controller
@RequestMapping("/user/hive")
public class HiveController {

    private static final String HIVE_CREATE_URL = "/create";
    private static final String HIVE_VIEW_URL = "/show/{id}";
    private static final String HIVE_ADD_USER_URL = "/insertuser/{hiveId}";
    private static final String HIVE_REMOVE_USER_URL = "/removeuser/{hiveId}";
    private static final String HIVE_ADD_POST_URL = "/post/{hiveId}";
    private static final String SHOW_HIVE = "hive/showHive";
    private static final String HIVE = "hive/hive";
    private static final String HIVE_VIEW = "/user/hive/show/";
    private static final String HIVE_IMAGE = "/image/{imagePath}";
    private static final String HIVE_URL = "/user/hive";

    private static final Logger log = LogManager.getLogger(HiveController.class);

    @Autowired
    private HiveService hiveService;

    @Autowired
    private PostService postService;

    @Autowired
    private ImageUploader imageUploader;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private PostValidator postValidator;

    @Autowired
    private HiveValidator hiveValidator;

    @InitBinder("post")
    private void initPostBinder(WebDataBinder binder) {
        binder.addValidators(postValidator);
    }

    @InitBinder("hive")
    private void hiveBinder(WebDataBinder binder) {
        binder.addValidators(hiveValidator);
    }

    @GetMapping
    public String viewHive(ModelMap model) {
        int userId = sessionHelper.getAuthUserIdFromSession();

        model.addAttribute("hiveList", userService.findById(userId).getHiveList());
        model.addAttribute("userIdInfo", new UserIdInfo());

        if (!model.containsAttribute("hive")) {
            model.addAttribute("hive", new Hive());
        }

        log.debug("AuthUser ID: {}", userId);

        return HIVE;
    }

    @GetMapping(value = HIVE_VIEW_URL)
    public String viewHivePage(Model model, @PathVariable("id") int id) {
        Hive hive = hiveService.retrieveHiveById(id);
        model.addAttribute("hive", hive);
        model.addAttribute("userList", hiveService.getUserNotInList(id));
        model.addAttribute("userListToRemove", hiveService.getUserListToRemove(id));
        model.addAttribute("noticeList", hiveService.getLatestNotice(hive.getNoticeList()));

        if (!model.containsAttribute("userIdInfo")) {
            model.addAttribute("userIdInfo", new UserIdInfo());
        }

        if (!model.containsAttribute("post")) {
            model.addAttribute("post", new Post());
        }

        log.debug("Created Hive: {}", hive.getName());

        return SHOW_HIVE;
    }

    @PostMapping(value = HIVE_ADD_USER_URL)
    public String addUser(@Valid @ModelAttribute("userIdInfo") UserIdInfo userIdInfo, BindingResult result, Model model,
                          RedirectAttributes redirectAttributes,
                          @PathVariable("hiveId") int hiveId) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "userIdInfo", result);
            redirectAttributes.addFlashAttribute("userIdInfo", userIdInfo);

            return redirectTo(HIVE_VIEW + hiveId);
        }

        model.addAttribute("userInfoId", userIdInfo);
        hiveService.saveUsersToHive(hiveId, userIdInfo.getUserIdList());

        log.debug("Member Added to hive : {}", userIdInfo.getUserIdList());

        return redirectTo(HIVE_VIEW + hiveId);
    }

    @PostMapping(value = HIVE_REMOVE_USER_URL)
    public String RemoveUser(@Valid @ModelAttribute("userIdInfo") UserIdInfo userIdInfo, BindingResult result,
                             Model model, RedirectAttributes redirectAttributes, @PathVariable("hiveId") int hiveId) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "userIdInfo", result);
            redirectAttributes.addFlashAttribute("userIdInfo", userIdInfo);

            return redirectTo(HIVE_VIEW + hiveId);
        }

        model.addAttribute("userInfoId", userIdInfo);
        hiveService.removeUsersFromHive(hiveId, userIdInfo.getUserIdList());

        log.debug("Member Removed from hive : {}", userIdInfo.getUserIdList());

        return redirectTo(HIVE_VIEW + hiveId);
    }

    @PostMapping(value = HIVE_CREATE_URL)
    public String saveHive(@Valid @ModelAttribute("hive") Hive hive, BindingResult result,
                           RedirectAttributes redirectAttributes,
                           @RequestParam MultipartFile file, Model model) throws IOException {

        if (result.hasErrors() || file.getSize() == 0) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "hive", result);
            redirectAttributes.addFlashAttribute("hive", hive);

            if (file == null || file.isEmpty()) {
                redirectAttributes.addFlashAttribute("fileError", "Please select picture");
            }

            return redirectTo(HIVE_URL);
        }

        model.addAttribute("hiveName", hive.getName());
        String filename = hive.getName().replaceAll(" ", "") + file.getOriginalFilename();
        hive.setImagePath(filename);
        int userId = sessionHelper.getAuthUserIdFromSession();
        hive.setCreator(userService.findById(userId));
        Hive newHive = hiveService.saveFirstUserToHive(hive, userId);
        hiveService.saveHive(newHive);

        if (!file.isEmpty()) {
            imageUploader.createImagesDirIfNeeded();
            model.addAttribute("message", imageUploader.createImage(filename, file));
        }

        log.debug("AuthUser ID: {}", userId);
        log.debug("New Hive Name: {}", hive.getName());

        return redirectTo(HIVE_VIEW + hiveService.getHiveByHiveName(newHive.getName()).getId());
    }

    @PostMapping(value = HIVE_ADD_POST_URL)
    public String savePost(@Valid @ModelAttribute("post") Post post, BindingResult result,
                           RedirectAttributes redirectAttributes, @PathVariable("hiveId") int hiveId) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "post", result);
            redirectAttributes.addFlashAttribute("post", post);

            return redirectTo(HIVE_VIEW + hiveId);
        }

        int userId = sessionHelper.getAuthUserIdFromSession();
        postService.savePost(userId, hiveId, post);

        log.debug("AuthUser ID: {}", userId);
        log.debug("Post: {}", post.getDescription());

        return redirectTo(HIVE_VIEW + hiveId);
    }

    @RequestMapping(value = HIVE_IMAGE)
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imagePath") String imageName) throws IOException {
        imageUploader.createImagesDirIfNeeded();
        File serverFile = new File(imageUploader.getImagesDirAbsolutePath() + imageName + ".png");

        return Files.readAllBytes(serverFile.toPath());
    }
}