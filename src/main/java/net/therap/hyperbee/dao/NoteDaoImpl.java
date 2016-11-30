package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.domain.enums.NoteType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static net.therap.hyperbee.utils.constant.DomainConstant.REMINDER_NOTE_COUNT_DASHBOARD;
import static net.therap.hyperbee.utils.constant.DomainConstant.STICKY_NOTE_COUNT_DASHBOARD;

/**
 * @author bashir
 * @since 11/22/16
 */
@Repository
public class NoteDaoImpl implements NoteDao {

    private static final String NOTE_ARCHIVE_SCHEDULER_NATIVE_QUERY = "UPDATE note n SET " +
            " n.display_status = 'INACTIVE' WHERE n.date_remind < curdate() AND n.date_remind IS NOT NULL;";

    private static final String NOTE_REMAINING_REMINDER_COUNT_QUERY = "SELECT COUNT(id) " +
            " FROM note n WHERE n.type='REMINDER' AND n.date_remind > now() AND n.user_id=:userId;";

    private static final String NOTE_STICKY_COUNT_QUERY = "SELECT COUNT(id) FROM note n " +
            " WHERE n.type='STICKY' AND n.user_id=:userId;";

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void save(Note note) {

        if (note.isNoteNew()) {

            em.persist(note);
        } else {

            note = em.merge(note);
        }
        em.flush();
    }

    @Override
    @Transactional
    public void markNoteAsInactiveForUser(int userId, int noteId) {

        em.createNamedQuery("Note.updateDisplayStatusForUser")
                .setParameter("userId", userId)
                .setParameter("noteId", noteId)
                .setParameter("displayStatus", DisplayStatus.INACTIVE)
                .executeUpdate();
        em.flush();
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

        return em.createNamedQuery("Note.findTopStickyNoteByUserId", Note.class)
                .setParameter("userId", userId)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .setParameter("type", NoteType.STICKY)
                .setMaxResults(STICKY_NOTE_COUNT_DASHBOARD)
                .getResultList();
    }

    @Override
    @Transactional
    public void markExpiredNoteAsInactive() {

        em.createNativeQuery(NOTE_ARCHIVE_SCHEDULER_NATIVE_QUERY).executeUpdate();
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

    public int getRemainingReminderCountForUser(int userId) {

        return em.createNativeQuery(NOTE_REMAINING_REMINDER_COUNT_QUERY)
                .setParameter("userId", userId)
                .getFirstResult();
    }

    public int getStickyNoteCountForUser(int userId) {

        return em.createNativeQuery(NOTE_STICKY_COUNT_QUERY)
                .setParameter("userId", userId)
                .getFirstResult();
    }
}
