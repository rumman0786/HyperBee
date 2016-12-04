package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.NoteDao;
import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static net.therap.hyperbee.utils.constant.Constant.STICKY_NOTE_COUNT_DASHBOARD;

/**
 * @author bashir
 * @since 11/22/16
 */
@Service
public class NoteServiceImpl implements NoteService {

    private static final Logger log = LogManager.getLogger(NoteServiceImpl.class);

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Note> findActiveNotesForUser(int userId) {

        return noteDao.findActiveNoteListByUserId(userId);
    }

    @Override
    public List<Note> findTopStickyNoteByUser(int userId) {
        log.debug("userId sticky note: " + userId);
        List<Note> noteList = noteDao.findTopStickyNoteByUser(STICKY_NOTE_COUNT_DASHBOARD, userId);

        return noteList;
    }

    @Override
    @Transactional
    public Note saveNoteForUser(Note note, int userId) {
        User user = userDao.findById(userId);
        note.setUser(user);

        return noteDao.save(note);
    }

    @Override
    @Transactional
    public void markNoteAsInactiveForUser(int userId, int noteId) {

        noteDao.markNoteAsInactiveForUser(userId, noteId);
    }

    @Override
    public List<Note> findUpcomingReminderNoteByUser(int userId) {

        return noteDao.findUpcomingReminderNoteByUser(userId);
    }

    @Override
    public int getRemainingReminderCountForUser(int userId) {

        return noteDao.getRemainingReminderCountForUser(userId);
    }

    @Override
    public int getStickyNoteCountForUser(int userId) {

        return noteDao.getStickyNoteCountForUser(userId);
    }

    @Override
    public int getReminderCountTodayForUser(int userId) {

        return noteDao.getReminderCountTodayForUser(userId);
    }
}
