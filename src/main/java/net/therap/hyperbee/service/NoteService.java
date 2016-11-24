package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.NoteDao;
import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.web.helper.NoteHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bashir
 * @since 11/22/16
 */
@Service
public class NoteService implements StickyNoteService {

    @Autowired
    NoteDao noteDao;

    @Autowired
    UserDao userDao;

    @Autowired
    NoteHelper noteHelper;

    @Override
    @Transactional
    public void createStickyNote(User user, Note note) {

        noteDao.createNoteAndUser(note, user);
    }

    @Override
    public List<Note> findActiveNotesForUser(int userId) {

        return noteDao.findActiveNoteListByUserId(userId);
    }

    @Override
    @Transactional
    public void saveNoteForUser(Note note, int userId) {

        User user = userDao.findById(userId);
        note.setUser(user);
        noteDao.create(note);
    }

    @Override
    @Transactional
    public void markNoteAsInactiveForUser(int userId, int noteId) {

        noteDao.markNoteAsInactiveForUser(userId, noteId);
    }
}
