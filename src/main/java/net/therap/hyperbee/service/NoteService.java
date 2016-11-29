package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.NoteDao;
import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.web.helper.NoteHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static net.therap.hyperbee.utils.constant.DomainConstant.STICKY_NOTE_COUNT_DASHBOARD;

/**
 * @author bashir
 * @since 11/22/16
 */
@Service
public class NoteService implements StickyNoteService {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

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

    public List<Note> findTopStickyNoteByUser(int userId) {

        List<Note> noteList = noteDao.findTopStickyNoteByUser(STICKY_NOTE_COUNT_DASHBOARD, userId);
        log.debug("Top Sticky Note Dashboard: "+noteList.size());

        return noteList;
    }

    @Override
    @Transactional
    public void saveNoteForUser(Note note, int userId) {

        User user = userDao.findById(userId);
        note.setUser(user);
        noteDao.save(note);
    }

    @Override
    @Transactional
    public void markNoteAsInactiveForUser(int userId, int noteId) {

        noteDao.markNoteAsInactiveForUser(userId, noteId);
    }
}
