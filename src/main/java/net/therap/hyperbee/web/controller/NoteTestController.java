package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.StickyNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Calendar;

/**
 * @author bashir
 * @since 11/22/16
 */
@Controller
public class NoteTestController {

    @Autowired
    private StickyNoteService noteService;

    @GetMapping("/user/note/save")
    public String saveNote() {

        User user = new User();
        user.setDisplayStatus(DisplayStatus.ACTIVE);
        user.setUsername("rakib2");
        user.setFirstName("BASHIR1");
        user.setEmail("asd23");

        Note note = new Note();
        note.setDisplayStatus(DisplayStatus.ACTIVE);
        note.setDescription("Test description of Note");
        note.setTitle("Title for Note");

        Calendar calendar = note.getDateCreated();
        calendar.setTimeInMillis(System.currentTimeMillis());

        Calendar calendar1 = note.getDateRemind();
        calendar1.setTimeInMillis(System.currentTimeMillis());

        noteService.createStickyNote(user, note);
        return "welcome";
    }

    @GetMapping("/user/notes")
    public String viewNotes() {
        return "notes";
    }

    @GetMapping("/user/note/add")
    public String addNotes(Model model) {

        model.addAttribute("noteCommand", new Note());
        return "noteForm";
    }
}
