package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.dao.NoticeDao;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.NoticeService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.helper.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static net.therap.hyperbee.utils.constant.Url.*;

/**
 * @author rumman
 * @since 11/22/16
 */
@Controller
@RequestMapping(value = NOTICE_BASE_URL)
public class NoticeController {

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionHelper sessionHelper;
//
//    @Autowired
//    @Qualifier("dishCommandValidator")
//    private Validator validator;
//
//    @InitBinder
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }

    @RequestMapping(value = NOTICE_LIST_URL, method = RequestMethod.GET)
    public String showNoticeList(ModelMap modelMap) {

        modelMap.addAttribute("page", "notice")
                .addAttribute("noticeList", noticeDao.findAll())
                .addAttribute("noticeAddUrl", NOTICE_BASE_URL)
                .addAttribute("deleteUrl", NOTICE_BASE_URL + NOTICE_DELETE_URL);

        return "notice/list_notice";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAddNoticeForm(ModelMap modelMap) {

        modelMap.addAttribute("page", "notice")
                .addAttribute("notice", new Notice())
                .addAttribute("noticeHeader", "Add Notice")
                .addAttribute("action", NOTICE_BASE_URL + NOTICE_ADD_URL)
                .addAttribute("displayStatusOptions", DisplayStatus.values());

        return "notice/form_notice";
    }

    @RequestMapping(value = NOTICE_ADD_URL, method = RequestMethod.POST)
    public String addNotice(@ModelAttribute("notice") Notice notice, HttpSession session
//                                  @RequestParam("dateExpired") String dateExpired
//                                      BindingResult bindingResult,
    ) {
//        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyy-mm-dd hh:mm:ss");
//
//        DateTime dt = formatter.parseDateTime(dateExpired);
//        notice.setDateExpired(dt.toGregorianCalendar());
        int sessionUserId = (sessionHelper.retrieveAuthUserFromSession(session)).getId();
        notice.setUser(userService.findById(sessionUserId));

//        if (bindingResult.hasErrors()) {
//            return new ModelAndView("dish/add-dish");
//        }

//        Dish dish = new Dish();
//        dish.setName(dishCommand.getName());
//        dish.setCalories(dishCommand.getCalories());
        noticeService.saveNotice(notice);
//        DateFormat df = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
//        Date date = null;
//        try {
//            date = df.parse(dateExpired);
//        } catch (ParseException e) {
//            e.printStackTrace();
////        }
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(dt);

//        if (status) {
//            redirectUrl += "?success=success";
//        } else {
//            redirectUrl += "?failure=failure";
//        }

        return "redirect:" + NOTICE_BASE_URL + NOTICE_LIST_URL;
    }

    @RequestMapping(value = "/{id}/**", method = RequestMethod.GET)
    public String showEditNoiceForm(@PathVariable("id") int id, ModelMap modelMap) {

        modelMap.addAttribute("page", "notice")
                .addAttribute("action", NOTICE_BASE_URL + NOTICE_UPDATE_URL)
                .addAttribute("noticeHeader", "Edit Notice")
                .addAttribute("notice", noticeDao.findById(id));

        return "notice/form_notice";
    }

    //
//    @RequestMapping(value = "/admin/edit-dish", method = RequestMethod.GET)
//    public ModelAndView showEditDish(HttpServletRequest request) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Dish dish = dishDao.findById(id);
//
//        DishCommand dishCommand = new DishCommand();
//        dishCommand.setId(dish.getId());
//        dishCommand.setName(dish.getName());
//        dishCommand.setCalories(dish.getCalories());
//
//        ModelAndView model = new ModelAndView("dish/edit-dish")
//                .addObject("dish", dishCommand)
//                .addObject("page", "dish");
//
//        return model;
//    }
//
    @RequestMapping(value = NOTICE_UPDATE_URL, method = RequestMethod.POST)
    public String editNotice(@ModelAttribute("notice") Notice notice, HttpSession session//, @Validated
                             //BindingResult bindingResult,Model model
    ) {
        int sessionUserId = (sessionHelper.retrieveAuthUserFromSession(session)).getId();
        notice.setUser(userService.findById(sessionUserId));

//        if (bindingResult.hasErrors()) {
//            return new ModelAndView("dish/edit-dish");
//        }

//        Dish dish = new Dish();
//        dish.setName(dishCommand.getName());
//        dish.setCalories(dishCommand.getCalories());
//        dish.setId(dishCommand.getId());

        noticeService.saveNotice(notice);
//        if (status) {
//            redirectUrl += "?success=success";
//        } else {
//            redirectUrl += "?failure=failure";
//        }

        return "redirect:" + NOTICE_BASE_URL + NOTICE_LIST_URL;
    }

    @RequestMapping(value = NOTICE_DELETE_URL, method = RequestMethod.POST)
    public String deleteDish(@RequestParam("id") int dishId) {
        noticeService.delete(dishId);
        return "redirect:" + NOTICE_BASE_URL + NOTICE_LIST_URL;
    }
}
