package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.NoteDao;
import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bashir
 * @since 11/22/16
 */
@Service
public class NoteService implements StickyNoteService {

    @Autowired
    NoteDao noteDao;

    @Override
    public void createStickyNote(User user, Note note) {

    }
}
