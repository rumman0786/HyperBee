package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.NoteService;
import net.therap.hyperbee.utils.Utils;
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

import static net.therap.hyperbee.utils.constant.Messages.*;
import static net.therap.hyperbee.utils.constant.Url.*;

/**
 * @author bashir
 * @since 11/22/16
 */
@Controller
public class NoteController {

    private static final Logger log = LogManager.getLogger(NoteController.class);
    private static final String NOTE_VIEW = "note/note_list";
    private static final String NOTE_VIEW_STICKY_URL = "/note/view/sticky";
    private static final String NOTE_VIEW_REMINDER_URL = "/note/view/reminder";

    @Autowired
    private NoteService noteService;

    @Autowired
    ActivityService activityService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private NoteHelper noteHelper;

    @Autowired
    private NoteDateTimeValidator noteDateTimeValidator;

    @Autowired
    private Utils utils;

    @InitBinder("noteCommand")
    private void noteInputInitBinder(WebDataBinder binder) {
        binder.addValidators(noteDateTimeValidator);
    }

    @GetMapping(NOTE_VIEW_URL)
    public String viewNotes(Model model, HttpSession session) {

        int userId = sessionHelper.getAuthUserIdFromSession();
        List<Note> noteList = noteService.findActiveNotesForUser(userId);

        model.addAttribute("noteList", noteList);

        if (!model.containsAttribute("noteCommand")) {
            model.addAttribute("noteCommand", new Note());
        }

        model.addAttribute("page", "note");
        activityService.archive(NOTE_PAGE_VIEW_ACTIVITY);

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
            activityService.archive(NOTE_SAVE_FAILED);

            log.debug("Note save failed: " + bindingResult);

            return utils.redirectTo(NOTE_VIEW_URL);
        }

        int userId = sessionHelper.getAuthUserIdFromSession();

        noteHelper.processNoteForSaving(note, dateRemindString);
        noteService.saveNoteForUser(note, userId);

        activityService.archive(NOTE_SAVE_ACTIVITY + " type: " + note.getNoteTypeAsString() + " Title:" + note.getTitle());

        redirectAttributes.addFlashAttribute("message", NOTE_SAVE_SUCCESS);
        redirectAttributes.addFlashAttribute("messageStyle", SUCCESS_HTML_CLASS);

        log.debug("Note successfully saved: userId" + userId + "note: " + note);
        sessionHelper.initializeNoteStatForUser();

        return utils.redirectTo(DONE_URL);
    }

    @PostMapping(NOTE_DELETE_URL)
    public String noteDelete(@PathVariable("id") int noteId, @PathVariable("type") String noteType,
                             HttpSession session, RedirectAttributes redirectAttributes,
                             @ModelAttribute("noteCommand") Note note, Model model) {

        int userId = sessionHelper.getAuthUserIdFromSession();
        noteService.markNoteAsInactiveForUser(userId, noteId);

        redirectAttributes.addFlashAttribute("message", NOTE_DELETE_SUCCESS);
        redirectAttributes.addFlashAttribute("messageStyle", FAILURE_HTML_CLASS);

        activityService.archive(NOTE_DELETE_ACTIVITY + " " + noteType);
        sessionHelper.initializeNoteStatForUser();

        log.debug("Note deleted: userId " + userId + " noteId: " + noteId + " noteType: " + noteType);

        return utils.redirectTo(DONE_URL);
    }

    @GetMapping(NOTE_VIEW_STICKY_URL)
    public String viewAllStickyNote(Model model) {

        int userId = sessionHelper.getAuthUserIdFromSession();
        List<Note> stickyNoteList = noteService.findStickyNoteByUser(userId);
        model.addAttribute("selectedNoteList", stickyNoteList);
        model.addAttribute("page", "Sticky Note");
        return NOTE_VIEW;
    }

    @GetMapping(NOTE_VIEW_REMINDER_URL)
    public String viewAllReminderNote(Model model) {

        int userId = sessionHelper.getAuthUserIdFromSession();
        List<Note> stickyNoteList = noteService.getReminderNoteForTodayByUser(userId);
        model.addAttribute("selectedNoteList", stickyNoteList);
        model.addAttribute("page", "Reminder Note");
        return NOTE_VIEW;
    }
}
