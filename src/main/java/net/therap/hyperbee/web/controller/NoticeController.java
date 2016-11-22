package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.dao.NoticeDao;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.service.NoticeService;
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
                .addAttribute("noticeList", noticeList);

        return "notice/list_notice";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAddNotice(ModelMap modelMap) {
        modelMap.addAttribute("page", "notice")
                .addAttribute("notice", new Notice());

        return "notice/form_notice";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String handleAddNotice(@ModelAttribute("notice") Notice notice//,
//                                      BindingResult bindingResult,
    ) {

//        if (bindingResult.hasErrors()) {
//            return new ModelAndView("dish/add-dish");
//        }

//        Dish dish = new Dish();
//        dish.setName(dishCommand.getName());
//        dish.setCalories(dishCommand.getCalories());
        noticeService.saveNotice(notice);

        String redirectUrl = "/dish-list";
//        if (status) {
//            redirectUrl += "?success=success";
//        } else {
//            redirectUrl += "?failure=failure";
//        }

        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/{id}/**", method = RequestMethod.GET)
    public String showEditNoice(@PathVariable("id") long id, ModelMap modelMap) {
        modelMap.addAttribute("page", "notice")
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
    public String deleteDish(@RequestParam("id") String dishId) {
        long id = Long.parseLong(dishId);
        noticeService.findNoticeById(id);

        return "redirect:/notice/list";
    }
}
