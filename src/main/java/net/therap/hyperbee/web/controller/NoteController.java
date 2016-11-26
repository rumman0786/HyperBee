package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.service.StickyNoteService;
import net.therap.hyperbee.web.helper.NoteHelper;
import net.therap.hyperbee.web.helper.SessionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.therap.hyperbee.utils.constant.Messages.NOTE_DELETE_SUCCESS;
import static net.therap.hyperbee.utils.constant.Messages.NOTE_SAVE_SUCCESS;
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

    @GetMapping(NOTE_VIEW_URL)
    public String viewNotes(Model model, HttpSession session) {

        int userId = sessionHelper.getUserIdFromSession(session);
        List<Note> noteList = noteService.findActiveNotesForUser(userId);

        model.addAttribute("noteList", noteList);
        model.addAttribute("noteCommand", new Note());

        return NOTE_VIEW_ALL;
    }

    @PostMapping(NOTE_SAVE_URL)
    public String saveNote(@ModelAttribute("noteCommand") Note note,
                           @RequestParam String dateRemindString,
                           Model model, HttpSession session) {

        int userId = sessionHelper.getUserIdFromSession(session);

        log.debug("AuthUser ID: " + userId);
        log.debug("Date Remind: " + dateRemindString);

        Calendar createdDate = note.getDateCreated();
        createdDate.setTimeInMillis(System.currentTimeMillis());

        Calendar remindDate = note.getDateRemind();
        noteHelper.setCalendarValFromString(remindDate, dateRemindString);
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
