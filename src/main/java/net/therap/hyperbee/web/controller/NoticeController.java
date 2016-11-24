package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.dao.NoticeDao;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.NoticeService;
import net.therap.hyperbee.service.UserService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
@Controller
@RequestMapping(value = "/notice")
public class NoticeController {

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserService userService;
//
//    @Autowired
//    @Qualifier("dishCommandValidator")
//    private Validator validator;
//
//    @InitBinder
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showNoticeList(ModelMap modelMap) {
        List<Notice> noticeList = noticeDao.findAll();

        modelMap.addAttribute("page", "notice")
                .addAttribute("noticeList", noticeList)
                .addAttribute("noticeAddUrl", "/notice")
                .addAttribute("deleteUrl", "/notice/delete");

        return "notice/list_notice";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAddNotice(ModelMap modelMap) {

        modelMap.addAttribute("page", "notice")
                .addAttribute("notice", new Notice())
                .addAttribute("noticeHeader", "Add Notice")
                .addAttribute("action", "/notice/add")
                .addAttribute("displayStatusOptions", DisplayStatus.values());

        return "notice/form_notice";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String handleAddNotice(@ModelAttribute("notice") Notice notice
//                                  @RequestParam("dateExpired") String dateExpired
//                                      BindingResult bindingResult,
    ) {
//        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyy-mm-dd hh:mm:ss");
//
//        DateTime dt = formatter.parseDateTime(dateExpired);
//        notice.setDateExpired(dt.toGregorianCalendar());
        //TODO after rayd is done insert logged in user
        notice.setUser(userService.findById(1));

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

        String redirectUrl = "/notice/list";
//        if (status) {
//            redirectUrl += "?success=success";
//        } else {
//            redirectUrl += "?failure=failure";
//        }

        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/{id}/**", method = RequestMethod.GET)
    public String showEditNoice(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("page", "notice")
                .addAttribute("action", "/notice/update")
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
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String handleEditNotice(@ModelAttribute("notice") Notice notice//, @Validated
                                   //BindingResult bindingResult,Model model
    ) {
        //TODO after rayd is done insert logged in user
        notice.setUser(userService.findById(1));
//        if (bindingResult.hasErrors()) {
//            return new ModelAndView("dish/edit-dish");
//        }

//        Dish dish = new Dish();
//        dish.setName(dishCommand.getName());
//        dish.setCalories(dishCommand.getCalories());
//        dish.setId(dishCommand.getId());

        noticeService.updateNotice(notice);
        String redirectUrl = "/notice/list";
//        if (status) {
//            redirectUrl += "?success=success";
//        } else {
//            redirectUrl += "?failure=failure";
//        }

        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteDish(@RequestParam("id") int dishId) {
        noticeService.delete(dishId);
        return "redirect:/notice/list";
    }
}
