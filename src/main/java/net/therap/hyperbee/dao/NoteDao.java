package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Note;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bashir
 * @since 11/22/16
 */
@Repository
public interface NoteDao {

    Note save(Note note);

    int markNoteAsInactiveForUser(int userId, int noteId);

    List<Note> findActiveNoteListByUserId(int userId);

    List<Note> findTopStickyNoteByUser(int numberOfNotes, int userId);

    int markExpiredNoteAsInactive();

    public List<Note> findStickyNoteByUser(int userId);

    List<Note> findUpcomingReminderNoteByUser(int userId);

    int getRemainingReminderCountForUser(int userId);

    int getStickyNoteCountForUser(int userId);

    int getReminderCountTodayForUser(int userId);

    public int getNextWeekReminderCountForUser(int userId);

    public List<Note> getReminderNoteForTodayByUser(int userId);
}
