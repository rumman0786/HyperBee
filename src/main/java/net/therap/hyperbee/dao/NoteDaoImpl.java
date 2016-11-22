package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author bashir
 * @since 11/22/16
 */
public class NoteDaoImpl implements NoteDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Note note) {

    }

    @Override
    public Note readById(int noteId) {
        return null;
    }

    @Override
    public void update(Note note) {

    }

    @Override
    public void deleteById(int noteId) {

    }

    @Override
    public void createNoteAndUser(Note note, User user) {
        user.getNoteList().add(note);
        entityManager.persist(user);
    }
}
