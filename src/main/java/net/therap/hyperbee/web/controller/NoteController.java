package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.StickyNoteService;
import net.therap.hyperbee.utils.constant.Url;
import net.therap.hyperbee.web.security.AuthUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

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

    @GetMapping(NOTE_VIEW_URL)
    public String viewNotes(Model model, HttpSession session) {
        int userId = getUserIdFromSession(session);

        List<Note> noteList = noteService.findActiveNotesForUser(userId);
        log.debug("NOTE LIST USER:: "+ Arrays.deepToString(noteList.toArray()));

        model.addAttribute("noteList", noteList);
        return NOTE_VIEW_ALL;
    }

    @GetMapping(NOTE_ADD_URL)
    public String addNotes(Model model) {

        model.addAttribute("noteCommand", new Note());
        return NOTE_ADD_VIEW;
    }

    @PostMapping(NOTE_SAVE_URL)
    public String saveNote(@ModelAttribute("noteCommand")
                               Note note, Model model, HttpSession session) {

        int userId= getUserIdFromSession(session);
        log.debug("AuthUser ID: "+userId);
        Calendar createdDate = note.getDateCreated();
        createdDate.setTimeInMillis(System.currentTimeMillis());
        Calendar remindDate = note.getDateRemind();
        remindDate.setTimeInMillis(System.currentTimeMillis());

        noteService.saveNoteForUser(note, userId);

        model.addAttribute("message", "Note saved");

        return SUCCESS_VIEW;
    }

    private int getUserIdFromSession(HttpSession session) {
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");
        return  authUser.getId();
    }
}
