package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.domain.enums.NoteType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

import static net.therap.hyperbee.utils.constant.Constant.REMINDER_NOTE_COUNT_DASHBOARD;
import static net.therap.hyperbee.utils.constant.Constant.STICKY_NOTE_COUNT_DASHBOARD;

/**
 * @author bashir
 * @since 11/22/16
 */
@Repository
public class NoteDaoImpl implements NoteDao {

    private static final Logger log = LogManager.getLogger(NoteDaoImpl.class);

    private static final String NOTE_ARCHIVE_SCHEDULER_NATIVE_QUERY = "UPDATE note n SET " +
            " n.display_status = 'INACTIVE' WHERE n.date_remind < curdate() AND n.date_remind IS NOT NULL;";

    private static final String NOTE_REMAINING_REMINDER_COUNT_QUERY = "SELECT COUNT(*) " +
            " FROM note n WHERE n.type='REMINDER' AND DATE(n.date_remind) >= curdate() " +
            " AND n.display_status = 'ACTIVE' AND n.user_id=?;";

    private static final String NOTE_STICKY_COUNT_QUERY = "SELECT COUNT(*) FROM note n " +
            " WHERE n.type='STICKY' AND n.display_status= 'ACTIVE' AND n.user_id=?;";

    private static final String NOTE_REMINDER_COUNT_TODAY = "SELECT COUNT(*) FROM note n WHERE n.type = 'REMINDER' " +
            " AND DATE(n.date_remind)=curdate() AND n.user_id=?;";

    private static final String NOTE_REMINDER_LIST_TODAY = "SELECT * FROM note n WHERE n.type = 'REMINDER' " +
            " AND DATE(n.date_remind)=curdate() AND n.user_id=?;";

    private static final String NOTE_REMINDER_COUNT_WEEK = "SELECT COUNT(*) FROM note n WHERE n.type = 'REMINDER' " +
            " AND DATE(n.date_remind) BETWEEN curdate() AND DATE_ADD(curdate(), INTERVAL 1 WEEK) AND n.user_id=?;";

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Note save(Note note) {
        if (note.isNew()) {
            em.persist(note);
        } else {
            note = em.merge(note);
        }
        em.flush();
        return note;
    }

    @Override
    @Transactional
    public int markNoteAsInactiveForUser(int userId, int noteId) {
        int rowUpdated = em.createNamedQuery("Note.updateDisplayStatusForUser")
                .setParameter("userId", userId)
                .setParameter("noteId", noteId)
                .setParameter("displayStatus", DisplayStatus.INACTIVE)
                .executeUpdate();
        em.flush();
        log.trace("markNoteAsInactive- no of rows updated :: ", rowUpdated);

        return rowUpdated;
    }

    @Override
    public List<Note> findActiveNoteListByUserId(int userId) {

        return em.createNamedQuery("Note.findNoteByUserId", Note.class)
                .setParameter("userId", userId)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .getResultList();
    }

    @Override
    public List<Note> findTopStickyNoteByUser(int numberOfNotes, int userId) {

        return em.createNamedQuery("Note.findStickyNoteByUserId", Note.class)
                .setParameter("userId", userId)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .setParameter("type", NoteType.STICKY)
                .setMaxResults(STICKY_NOTE_COUNT_DASHBOARD)
                .getResultList();
    }

    @Override
    public List<Note> findStickyNoteByUser(int userId) {

        return em.createNamedQuery("Note.findStickyNoteByUserId", Note.class)
                .setParameter("userId", userId)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .setParameter("type", NoteType.STICKY)
                .getResultList();
    }

    @Override
    @Transactional
    public int markExpiredNoteAsInactive() {
        return em.createNativeQuery(NOTE_ARCHIVE_SCHEDULER_NATIVE_QUERY).executeUpdate();
    }

    @Override
    public List<Note> findUpcomingReminderNoteByUser(int userId) {

        return em.createNamedQuery("Note.reminderForUserDash", Note.class)
                .setParameter("userId", userId)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .setParameter("type", NoteType.REMINDER)
                .setMaxResults(REMINDER_NOTE_COUNT_DASHBOARD)
                .getResultList();
    }

    @Override
    public int getRemainingReminderCountForUser(int userId) {
        Query query = em.createNativeQuery(NOTE_REMAINING_REMINDER_COUNT_QUERY);
        query.setParameter(1, userId);

        return ((BigInteger) query.getSingleResult()).intValue();
    }

    @Override
    public int getStickyNoteCountForUser(int userId) {
        Query query = em.createNativeQuery(NOTE_STICKY_COUNT_QUERY);
        query.setParameter(1, userId);

        return ((BigInteger) query.getSingleResult()).intValue();
    }

    @Override
    public int getReminderCountTodayForUser(int userId) {
        Query query = em.createNativeQuery(NOTE_REMINDER_COUNT_TODAY);
        query.setParameter(1, userId);

        return ((BigInteger) query.getSingleResult()).intValue();
    }

    @Override
    public int getNextWeekReminderCountForUser(int userId) {
        Query query = em.createNativeQuery(NOTE_REMINDER_COUNT_WEEK);
        query.setParameter(1, userId);

        return ((BigInteger) query.getSingleResult()).intValue();
    }

    @Override
    public List<Note> getReminderNoteForTodayByUser(int userId) {
        Query query = em.createNativeQuery(NOTE_REMINDER_LIST_TODAY, Note.class);
        query.setParameter(1, userId);

        List<Note> noteList = query.getResultList();
        return noteList;
    }
}
