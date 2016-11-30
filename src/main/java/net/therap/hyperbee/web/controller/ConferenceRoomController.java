package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.ConferenceRoom;
import net.therap.hyperbee.service.ConferenceRoomService;
import net.therap.hyperbee.web.validator.ConferenceRoomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
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


    @Autowired
    private ConferenceRoomValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
    }

    @GetMapping(value = CONFERENCE_ROOM_LIST_URL)
    public String showConferenceRoomList(ModelMap modelMap) {

        modelMap.addAttribute("page", "conferenceRoom")
                .addAttribute("conferenceRoomList", conferenceRoomService.findAllConferenceRoom())
                .addAttribute("conferenceRoomAddUrl", CONFERENCE_ROOM_BASE_URL)
                .addAttribute("deleteUrl", CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_DELETE_URL);

        return "conference_room/list_conference_room";
    }

    @GetMapping
    public String showAddConferenceRoomForm(ModelMap modelMap) {

        modelMap.addAttribute("page", "conferenceRoom")
                .addAttribute("conferenceRoom", new ConferenceRoom())
                .addAttribute("pageHeader", "Add Conference Room")
                .addAttribute("action", CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_ADD_URL);

        return "conference_room/form_conference_room";
    }


    @PostMapping(value = CONFERENCE_ROOM_ADD_URL)
    public String AddConferenceRoom(@ModelAttribute("conferenceRoom") @Validated ConferenceRoom conferenceRoom,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "conference_room/form_conference_room";
        }

        conferenceRoomService.saveConferenceRoom(conferenceRoom);

        return "redirect:" + CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_LIST_URL;
    }

    @GetMapping(value = "/{id}/**")
    public String showEditConferenceRoomForm(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("page", "conferenceRoom")
                .addAttribute("action", CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_UPDATE_URL)
                .addAttribute("pageHeader", "Edit ConferemceRoom")
                .addAttribute("conferenceRoom", conferenceRoomService.findConferenceRoomById(id));

        return "conference_room/form_conference_room";
    }


    @PostMapping(value = CONFERENCE_ROOM_UPDATE_URL)
    public String editAddConferenceRoom(@ModelAttribute("conferenceRoom") @Validated ConferenceRoom conferenceRoom,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "conference_room/form_conference_room";
        }

        conferenceRoomService.saveConferenceRoom(conferenceRoom);

        return "redirect:" + CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_LIST_URL;
    }

    @PostMapping(value = CONFERENCE_ROOM_DELETE_URL)
    public String deleteConferenceRoom(@RequestParam("id") int conferenceRoomId) {
        conferenceRoomService.delete(conferenceRoomId);

        return "redirect:" + CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_LIST_URL;
    }
}
