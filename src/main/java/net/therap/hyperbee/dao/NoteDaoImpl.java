package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.domain.enums.NoteType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static net.therap.hyperbee.utils.constant.DomainConstant.STICKY_NOTE_COUNT_DASHBOARD;

/**
 * @author bashir
 * @since 11/22/16
 */
@Repository
public class NoteDaoImpl implements NoteDao {

    private static final String NOTE_ARCHIVE_SCHEDULER_NATIVE_QUERY = "UPDATE note n SET " +
            " n.display_status = 'INACTIVE' WHERE n.date_remind < curdate() AND n.date_remind IS NOT NULL;";

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
    @Transactional
    public void createNoteAndUser(Note note, User user) {
        user.getNoteList().add(note);
        em.persist(user);
        em.flush();
    }

    @Override
    @Transactional
    public void saveNoteForUser(Note note, User user) {

        user.getNoteList().add(note);
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
}
