package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.HiveService;
import net.therap.hyperbee.service.NoticeService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.editor.HiveEditor;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.validator.NoticeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;

import static net.therap.hyperbee.utils.constant.Constant.*;
import static net.therap.hyperbee.utils.constant.Messages.*;
import static net.therap.hyperbee.utils.constant.Url.DONE_URL;

/**
 * @author rumman
 * @since 11/22/16
 */
@Controller
@RequestMapping(value = "/notice")
public class NoticeController {

    private static final Logger log = LogManager.getLogger(NoticeController.class);

    private static final String NOTICE_BASE_URL = "/notice";
    private static final String NOTICE_LIST_URL = "/list";
    private static final String NOTICE_ADD_URL = "/add";
    private static final String NOTICE_EDIT_URL = "/{id}/**";
    private static final String NOTICE_UPDATE_URL = "/update";
    private static final String NOTICE_DELETE_URL = "/delete";

    private static final String NOTICE_LIST_VIEW = "notice/list_notice";
    private static final String NOTICE_FORM_VIEW = "notice/form_notice";

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserService userService;

    @Autowired
    private HiveService hiveService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private NoticeValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);

        binder.registerCustomEditor(Hive.class, "hiveList", new HiveEditor());
    }


    @RequestMapping(value = NOTICE_LIST_URL, method = RequestMethod.GET)
    public String showNoticeList(ModelMap modelMap) {
        modelMap.addAttribute("page", NOTICE_HTML_PAGE_ACTIVE_KEY)
                .addAttribute("noticeList", noticeService.findAllNotice())
                .addAttribute("noticeAddUrl", NOTICE_BASE_URL)
                .addAttribute("isAdmin", sessionHelper.getAuthUserFromSession().isAdmin())
                .addAttribute("deleteUrl", NOTICE_BASE_URL + NOTICE_DELETE_URL);

        log.debug(NOTICE_VIEWED);
        activityService.archive(NOTICE_LIST_VIEWED);

        return NOTICE_LIST_VIEW;
    }

    @GetMapping
    public String showAddNoticeForm(ModelMap modelMap) {
        if (!modelMap.containsAttribute("notice")) {
            modelMap.addAttribute("notice", new Notice());
        }

        modelMap.addAttribute("page", NOTICE_HTML_PAGE_ACTIVE_KEY)
                .addAttribute("noticeHeader", NOTICE_PAGE_ADD_HEADER)
                .addAttribute("action", NOTICE_BASE_URL + NOTICE_ADD_URL)
                .addAttribute("hiveList", hiveService.getAllHive())
                .addAttribute("displayStatusOptions", DisplayStatus.values());

        log.debug(NOTICE_ADD_VIEWED);

        return NOTICE_FORM_VIEW;
    }

    @PostMapping(value = NOTICE_ADD_URL)
    public String addNotice(@ModelAttribute("notice") Notice notice,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        int sessionUserId = (sessionHelper.getAuthUserFromSession()).getId();
        notice.setUser(userService.findById(sessionUserId));

        validator.validate(notice, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "notice", bindingResult)
                    .addFlashAttribute("notice", notice);

            log.debug(NOTICE_SAVE_ERROR);

            return "redirect:" + NOTICE_BASE_URL;
        }

        noticeService.saveNoticeAndArchive(notice, NOTICE_SAVED);
        log.debug(NOTICE_SAVED);

        redirectAttributes.addFlashAttribute(DONE_PAGE_KEY_HTML_MESSAGE, NOTICE_SUCCESS)
                .addFlashAttribute(DONE_PAGE_KEY_HTML_TITLE, NOTICE_SAVED)
                .addFlashAttribute(DONE_PAGE_KEY_HTML_MESSAGE_STYLE, SUCCESS_HTML_CLASS);

        return "redirect:" + DONE_URL;
    }

    @GetMapping(value = NOTICE_EDIT_URL)
    public String showEditNoticeForm(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("page", NOTICE_HTML_PAGE_ACTIVE_KEY)
                .addAttribute("action", NOTICE_BASE_URL + NOTICE_UPDATE_URL)
                .addAttribute("noticeHeader", NOTICE_PAGE_EDIT_HEADER)
                .addAttribute("hiveList", hiveService.getAllHive())
                .addAttribute("notice", noticeService.findNoticeById(id));

        log.debug(NOTICE_EDIT_VIEWED);

        return NOTICE_FORM_VIEW;
    }

    @PostMapping(value = NOTICE_UPDATE_URL)
    public String editNotice(@ModelAttribute("notice") Notice notice,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        int sessionUserId = (sessionHelper.getAuthUserFromSession()).getId();
        notice.setUser(userService.findById(sessionUserId));

        validator.validate(notice, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "notice", bindingResult)
                    .addFlashAttribute("notice", notice);

            log.debug(NOTICE_SAVE_ERROR);

            return "redirect:" + NOTICE_BASE_URL;
        }

        noticeService.saveNoticeAndArchive(notice, NOTICE_MODIFIED);
        log.debug(NOTICE_MODIFIED);

        redirectAttributes.addFlashAttribute(DONE_PAGE_KEY_HTML_MESSAGE, NOTICE_SUCCESS)
                .addFlashAttribute(DONE_PAGE_KEY_HTML_TITLE, NOTICE_MODIFIED)
                .addFlashAttribute(DONE_PAGE_KEY_HTML_MESSAGE_STYLE, SUCCESS_HTML_CLASS);

        return "redirect:" + DONE_URL;
    }

    @PostMapping(value = NOTICE_DELETE_URL)
    public String deleteNotice(@RequestParam("id") int noticeId) {
        noticeService.deleteNoticeAndArchive(noticeId, NOTICE_DELETED);
        log.debug(NOTICE_DELETED);

        return "redirect:" + NOTICE_BASE_URL + NOTICE_LIST_URL;
    }
}