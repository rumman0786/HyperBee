package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.ConferenceRoom;
import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.Reservation;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.domain.enums.ReservationStatus;
import net.therap.hyperbee.service.*;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.validator.NoticeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.GregorianCalendar;

import static net.therap.hyperbee.utils.constant.Url.*;

/**
 * @author rumman
 * @since 11/22/16
 */
@Controller
@RequestMapping(value = RERVATION_BASE_URL)
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ConferenceRoomService conferenceRoomService;

    @Autowired
    private SessionHelper sessionHelper;

//    @Autowired
//    private NoticeValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);

        binder.registerCustomEditor(ConferenceRoom.class, "conferenceRoom", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                ConferenceRoom conferenceRoom = conferenceRoomService.findConferenceRoomById(Integer.parseInt(text));
                setValue(conferenceRoom);
            }
        });
    }


    @GetMapping(value = RERVATION_LIST_URL)
    public String showReservationList(ModelMap modelMap) {

        modelMap.addAttribute("page", "reservation")
                .addAttribute("reservationList", reservationService.findAllReservation())
                .addAttribute("actionUrl", RERVATION_BASE_URL)
                .addAttribute("deleteUrl", RERVATION_BASE_URL + RERVATION_DELETE_URL);

        return "reservation/list_reservation";
    }

    @GetMapping
    public String showAddReservationForm(ModelMap modelMap) {

        modelMap.addAttribute("page", "reservation")
                .addAttribute("reservation", new Reservation())
                .addAttribute("pageHeader", "Add Reservation")
                .addAttribute("action", RERVATION_BASE_URL + RERVATION_ADD_URL)
                .addAttribute("roomList", conferenceRoomService.findAllConferenceRoom())
                .addAttribute("reservationStatusOptions", ReservationStatus.values());

        return "reservation/form_reservation";
    }

    @PostMapping(value = RERVATION_ADD_URL)
    public String addReservation(@ModelAttribute("reservation") Reservation reservation,
                            BindingResult bindingResult,
                            @RequestParam("reservationFrom") String reservationFrom,
                            @RequestParam("reservationTo") String reservationTo) {
        System.out.println(reservationFrom);
        System.out.println(reservationTo);
        int sessionUserId = (sessionHelper.retrieveAuthUserFromSession()).getId();
        reservation.setUser(userService.findById(sessionUserId));

//        if (bindingResult.hasErrors()) {
//            return "reservation/form_reservation";
//        }

        reservationService.saveReservation(reservation);
        return "redirect:" + RERVATION_BASE_URL + RERVATION_LIST_URL;
    }
//
//    @RequestMapping(value = "/{id}/**", method = RequestMethod.GET)
//    public String showEditNoiceForm(@PathVariable("id") int id, ModelMap modelMap) {
//        modelMap.addAttribute("page", "notice")
//                .addAttribute("action", RERVATION_BASE_URL + RERVATION_UPDATE_URL)
//                .addAttribute("noticeHeader", "Edit Notice")
//                .addAttribute("hiveList", hiveService.getHiveListByUserId(sessionHelper.getUserIdFromSession()))
//                .addAttribute("notice", noticeService.findNoticeById(id));
//
//        return "notice/form_notice";
//    }
//
//    @RequestMapping(value = RERVATION_UPDATE_URL, method = RequestMethod.POST)
//    public String editNotice(@ModelAttribute("notice") Notice notice,
//                             BindingResult bindingResult) {
//        int sessionUserId = (sessionHelper.retrieveAuthUserFromSession()).getId();
//        notice.setUser(userService.findById(sessionUserId));
//
//        noticeService.saveNotice(notice);
//
//        return "redirect:" + RERVATION_BASE_URL + RERVATION_LIST_URL;
//    }
//
//    @RequestMapping(value = RERVATION_DELETE_URL, method = RequestMethod.POST)
//    public String deleteNotice(@RequestParam("id") int noticeId) {
//        noticeService.delete(noticeId);
//
//        return "redirect:" + RERVATION_BASE_URL + RERVATION_LIST_URL;
//    }
}
