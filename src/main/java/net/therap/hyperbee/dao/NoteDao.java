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

    void save(Note note);

    void markNoteAsInactiveForUser(int userId, int noteId);

    void createNoteAndUser(Note note, User user);

    void saveNoteForUser(Note note, User user);

    List<Note> findActiveNoteListByUserId(int userId);

    List<Note> findTopStickyNoteByUser(int numberOfNotes, int userId);

    void markExpiredNoteAsInactive();
}
