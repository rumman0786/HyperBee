package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.ConferenceRoom;
import net.therap.hyperbee.service.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static net.therap.hyperbee.utils.constant.Url.*;

/**
 * @author rumman
 * @since 11/27/16
 */
@Controller
@RequestMapping(value = CONFERENCE_ROOM_BASE_URL)
public class ConferenceRoomController {

    @Autowired
    private ConferenceRoomService conferenceRoomService;

//
//    @Autowired
//    @Qualifier("dishCommandValidator")
//    private Validator validator;
//
//    @InitBinder
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }

    @RequestMapping(value = CONFERENCE_ROOM_LIST_URL, method = RequestMethod.GET)
    public String showConferenceRoomList(ModelMap modelMap) {

        modelMap.addAttribute("page", "conferenceRoom")
                .addAttribute("conferenceRoomList", conferenceRoomService.findAllConferenceRoom())
                .addAttribute("conferenceRoomAddUrl", CONFERENCE_ROOM_BASE_URL)
                .addAttribute("deleteUrl", CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_DELETE_URL);

        return "conference_room/list_conference_room";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAddConferenceRoomForm(ModelMap modelMap) {

        modelMap.addAttribute("page", "conferenceRoom")
                .addAttribute("conferenceRoom", new ConferenceRoom())
                .addAttribute("pageHeader", "Add Conference Room")
                .addAttribute("action", CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_ADD_URL);

        return "conference_room/form_conference_room";
    }


    @RequestMapping(value = CONFERENCE_ROOM_ADD_URL, method = RequestMethod.POST)
    public String AddConferenceRoom(@ModelAttribute("conferenceRoom") ConferenceRoom conferenceRoom
//                                      BindingResult bindingResult,
    ) {


//        if (bindingResult.hasErrors()) {
//            return new ModelAndView("dish/add-dish");
//        }

//        Dish dish = new Dish();
//        dish.setName(dishCommand.getName());
//        dish.setCalories(dishCommand.getCalories());
        conferenceRoomService.saveConferenceRoom(conferenceRoom);
//
//        if (status) {
//            redirectUrl += "?success=success";
//        } else {
//            redirectUrl += "?failure=failure";
//        }

        return "redirect:" + CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_LIST_URL;
    }

    @RequestMapping(value = "/{id}/**", method = RequestMethod.GET)
    public String showEditConferenceRoomForm(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("page", "conferenceRoom")
                .addAttribute("action", CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_UPDATE_URL)
                .addAttribute("pageHeader", "Edit ConferemceRoom")
                .addAttribute("conferenceRoom", conferenceRoomService.findConferenceRoomById(id));

        return "conference_room/form_conference_room";
    }


    @RequestMapping(value = CONFERENCE_ROOM_UPDATE_URL, method = RequestMethod.POST)
    public String editAddConferenceRoom(@ModelAttribute("conferenceRoom") ConferenceRoom conferenceRoom
                                        //BindingResult bindingResult,Model model
    ) {
//        if (bindingResult.hasErrors()) {
//            return new ModelAndView("dish/edit-dish");
//        }

//        Dish dish = new Dish();
//        dish.setName(dishCommand.getName());
//        dish.setCalories(dishCommand.getCalories());
//        dish.setId(dishCommand.getId());

        conferenceRoomService.saveConferenceRoom(conferenceRoom);
//        if (status) {
//            redirectUrl += "?success=success";
//        } else {
//            redirectUrl += "?failure=failure";
//        }

        return "redirect:" + CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_LIST_URL;
    }

    @RequestMapping(value = CONFERENCE_ROOM_DELETE_URL, method = RequestMethod.POST)
    public String deleteConferenceRoom(@RequestParam("id") int conferenceRoomId) {
        conferenceRoomService.delete(conferenceRoomId);
        return "redirect:" + CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_LIST_URL;
    }
}
