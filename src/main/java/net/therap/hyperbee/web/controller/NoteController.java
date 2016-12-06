package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.service.NoteService;
import net.therap.hyperbee.web.helper.NoteHelper;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.validator.NoteDateTimeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static net.therap.hyperbee.utils.Utils.redirectTo;
import static net.therap.hyperbee.utils.constant.Messages.*;
import static net.therap.hyperbee.utils.constant.Url.DONE_URL;

/**
 * @author bashir
 * @since 11/22/16
 */
@Controller
public class NoteController {

    private static final Logger log = LogManager.getLogger(NoteController.class);

    private static final String NOTE_VIEW = "note/note_list";
    private static final String NOTE_VIEW_STICKY_URL = "/note/view/sticky";
    private static final String NOTE_VIEW_TODAY_REMINDER_URL = "/note/view/reminder/today";
    private static final String NOTE_VIEW_WEEKLY_REMINDER_URL = "/note/view/reminder/week";
    private static final String NOTE_ALL_REMINDER_URL = "/note/view/reminder/all";
    private static final String NOTE_VIEW_URL = "/notes";
    private static final String NOTE_SAVE_URL = "/note/save";
    private static final String NOTE_VIEW_ALL = "note/notes";
    private static final String NOTE_DELETE_URL = "/note/delete/{type}/{id}";

    @Autowired
    private NoteService noteService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private NoteHelper noteHelper;

    @Autowired
    private NoteDateTimeValidator noteDateTimeValidator;

    @InitBinder("noteCommand")
    private void noteInputInitBinder(WebDataBinder binder) {
        binder.addValidators(noteDateTimeValidator);
    }

    @GetMapping(NOTE_VIEW_URL)
    public String viewNotes(Model model, HttpSession session) {

        int userId = sessionHelper.getUserIdFromSession();
        List<Note> noteList = noteService.findActiveNotesForUser(userId);

        model.addAttribute("noteList", noteList);

        if (!model.containsAttribute("noteCommand")) {
            model.addAttribute("noteCommand", new Note());
        }

        model.addAttribute("page", "note");

        return NOTE_VIEW_ALL;
    }

    @PostMapping(NOTE_SAVE_URL)
    public String saveNote(@Valid @ModelAttribute("noteCommand") Note note,
                           BindingResult bindingResult, @RequestParam String dateRemindString,
                           RedirectAttributes redirectAttributes,
                           Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "noteCommand", bindingResult);
            redirectAttributes.addFlashAttribute("noteCommand", note);

            log.debug("Note save failed, errors= {} ", bindingResult);

            return redirectTo(NOTE_VIEW_URL);
        }

        int userId = sessionHelper.getUserIdFromSession();

        noteHelper.processNoteForSaving(note, dateRemindString);
        noteService.saveNoteForUser(note, userId);

        redirectAttributes.addFlashAttribute("message", NOTE_SAVE_SUCCESS);
        redirectAttributes.addFlashAttribute("messageStyle", SUCCESS_HTML_CLASS);

        log.debug("Note successfully saved: userId={}, note={}", userId, note);
        sessionHelper.initializeNoteStatForUser(userId);

        return redirectTo(DONE_URL);
    }

    @PostMapping(NOTE_DELETE_URL)
    public String noteDelete(@PathVariable("id") int noteId, @PathVariable("type") String noteType,
                             HttpSession session, RedirectAttributes redirectAttributes,
                             @ModelAttribute("noteCommand") Note note, Model model) {

        int userId = sessionHelper.getUserIdFromSession();
        noteService.markNoteAsInactiveForUser(userId, noteId);

        redirectAttributes.addFlashAttribute("message", NOTE_DELETE_SUCCESS);
        redirectAttributes.addFlashAttribute("messageStyle", FAILURE_HTML_CLASS);

        sessionHelper.initializeNoteStatForUser(userId);

        log.debug("Note deleted: userId={}, noteId={}, noteType={} ", userId, noteId, noteType);

        return redirectTo(DONE_URL);
    }

    @GetMapping(NOTE_VIEW_STICKY_URL)
    public String viewAllStickyNote(Model model) {

        int userId = sessionHelper.getUserIdFromSession();
        List<Note> stickyNoteList = noteService.findStickyNoteByUser(userId);
        model.addAttribute("selectedNoteList", stickyNoteList);
        model.addAttribute("page", "Sticky Note");
        return NOTE_VIEW;
    }

    @GetMapping(NOTE_VIEW_TODAY_REMINDER_URL)
    public String viewReminderNoteToday(Model model) {

        int userId = sessionHelper.getUserIdFromSession();
        List<Note> stickyNoteList = noteService.getReminderNoteForTodayByUser(userId);
        model.addAttribute("selectedNoteList", stickyNoteList);
        model.addAttribute("page", "Reminder Note");
        return NOTE_VIEW;
    }

    @GetMapping(NOTE_VIEW_WEEKLY_REMINDER_URL)
    public String viewReminderNoteNextWeek(Model model) {

        int userId = sessionHelper.getUserIdFromSession();
        List<Note> stickyNoteList = noteService.getReminderNoteForNextWeekByUser(userId);
        model.addAttribute("selectedNoteList", stickyNoteList);
        model.addAttribute("page", "Reminder Note");
        return NOTE_VIEW;
    }

    @GetMapping(NOTE_ALL_REMINDER_URL)
    public String getAllReminder(Model model) {

        int userId = sessionHelper.getUserIdFromSession();
        List<Note> stickyNoteList = noteService.findAllReminderNoteByUser(userId);
        model.addAttribute("selectedNoteList", stickyNoteList);
        model.addAttribute("page", "Reminder Note");
        return NOTE_VIEW;
    }
}
