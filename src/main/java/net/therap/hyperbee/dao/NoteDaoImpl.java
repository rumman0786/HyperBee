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

/**
 * @author bashir
 * @since 11/22/16
 */
@Repository
public class NoteDaoImpl implements NoteDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public void create(Note note) {

        em.persist(note);
        em.flush();
    }

    @Override
    public Note readById(int noteId) {
        return null;
    }

    @Override
    public void update(Note note) {

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
                .setMaxResults(3)
                .getResultList();
    }

    @Override
    @Transactional
    public void markExpiredNoteAsInactive() {

        String nativeQuery = "UPDATE note n SET n.display_status = 'INACTIVE' WHERE n.date_remind < curdate() " +
                " AND n.date_remind IS NOT NULL;";

        em.createNativeQuery(nativeQuery).executeUpdate();
    }
}
