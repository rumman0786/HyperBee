package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.ConferenceRoom;
import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.Reservation;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.domain.enums.ReservationStatus;
import net.therap.hyperbee.service.*;
import net.therap.hyperbee.web.helper.ReservationHelper;
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

    @Autowired
    private ReservationHelper reservationHelper;

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

        int sessionUserId = (sessionHelper.retrieveAuthUserFromSession()).getId();
        reservation.setUser(userService.findById(sessionUserId));
        reservation.setReservationFrom(reservationHelper.getCalendarFromString(reservationFrom));
        reservation.setReservationTo(reservationHelper.getCalendarFromString(reservationTo));

//        if (bindingResult.hasErrors()) {
//            return "reservation/form_reservation";
//        }

        reservationService.saveReservation(reservation);
        return "redirect:" + RERVATION_BASE_URL + RERVATION_LIST_URL;
    }

    @GetMapping(value = "/{id}/**")
    public String showEditReservationForm(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("page", "reservation")
                .addAttribute("pageHeader", "Edit Reservation")
                .addAttribute("action", RERVATION_BASE_URL + RERVATION_UPDATE_URL)
                .addAttribute("reservation", reservationService.findReservationById(id))
                .addAttribute("roomList", conferenceRoomService.findAllConferenceRoom())
                .addAttribute("reservationStatusOptions", ReservationStatus.values());

        return "reservation/form_reservation";
    }

    @PostMapping(value = RERVATION_UPDATE_URL)
    public String editReservation(@ModelAttribute("reservation") Reservation reservation,
                             BindingResult bindingResult,
                             @RequestParam("reservationFrom") String reservationFrom,
                             @RequestParam("reservationTo") String reservationTo) {

        int sessionUserId = (sessionHelper.retrieveAuthUserFromSession()).getId();
        reservation.setUser(userService.findById(sessionUserId));
        reservation.setReservationFrom(reservationHelper.getCalendarFromString(reservationFrom));
        reservation.setReservationTo(reservationHelper.getCalendarFromString(reservationTo));
        reservationService.saveReservation(reservation);

        return "redirect:" + RERVATION_BASE_URL + RERVATION_LIST_URL;
    }

    @PostMapping(value = RERVATION_DELETE_URL)
    public String deleteReservation(@RequestParam("id") int reservationId) {
        reservationService.delete(reservationId);

        return "redirect:" + RERVATION_BASE_URL + RERVATION_LIST_URL;
    }
}
