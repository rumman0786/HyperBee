package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bashir
 * @since 11/22/16
 */
@Repository
public interface NoteDao {

    public void create(Note note);

    public Note readById(int noteId);

    public void update(Note note);

    public void deleteById(int noteId);

    public void createNoteAndUser(Note note, User user);

    public void saveNoteForUser(Note note, User user);

    public List<Note> findActiveNoteListByUserId(int userId);
}
