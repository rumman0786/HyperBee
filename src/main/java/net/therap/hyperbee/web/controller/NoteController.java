package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.service.StickyNoteService;
import net.therap.hyperbee.web.helper.NoteHelper;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.validator.NoteDateTimeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

import static net.therap.hyperbee.utils.constant.Messages.*;
import static net.therap.hyperbee.utils.constant.Url.*;

/**
 * @author bashir
 * @since 11/22/16
 */
@Controller
public class NoteController {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    @Autowired
    private StickyNoteService noteService;

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

        int userId = sessionHelper.getUserIdFromSession(session);
        List<Note> noteList = noteService.findActiveNotesForUser(userId);

        model.addAttribute("noteList", noteList);
        model.addAttribute("noteCommand", new Note());

        return NOTE_VIEW_ALL;
    }

    @PostMapping(NOTE_SAVE_URL)
    public String saveNote(@Valid @ModelAttribute("noteCommand") Note note,
                           BindingResult bindingResult, @RequestParam String dateRemindString,
                           Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {

            log.debug("ERROR IN SAVING NOTE");
            model.addAttribute("message", NOTE_SAVE_FAILURE);
            model.addAttribute("redirectUrl", NOTE_VIEW_URL);
            model.addAttribute("messageStyle", "error");
            return SUCCESS_VIEW;
        }

        int userId = sessionHelper.getUserIdFromSession(session);

        log.debug("AuthUser ID: " + userId);
        log.debug("Date Remind: " + dateRemindString);

        Calendar createdDate = note.getDateCreated();
        createdDate.setTimeInMillis(System.currentTimeMillis());

        noteHelper.setCalendarValFromString(note, dateRemindString);
        noteService.saveNoteForUser(note, userId);

        model.addAttribute("message", NOTE_SAVE_SUCCESS);
        model.addAttribute("redirectUrl", NOTE_VIEW_URL);

        return SUCCESS_VIEW;
    }

    @PostMapping(NOTE_DELETE_URL)
    public String noteDelete(@PathVariable("id") int noteId, HttpSession session,
                             @ModelAttribute("noteCommand") Note note, Model model) {

        noteService.markNoteAsInactiveForUser(sessionHelper.getUserIdFromSession(session), noteId);
        log.debug("Selected note ID Delete: " + noteId);

        model.addAttribute("message", NOTE_DELETE_SUCCESS);
        model.addAttribute("redirectUrl", NOTE_VIEW_URL);

        return SUCCESS_VIEW;
    }
}
