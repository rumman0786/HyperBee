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

    List<Note> findStickyNoteByUser(int userId);

    List<Note> findUpcomingReminderNoteByUser(int userId);

    int getRemainingReminderCountForUser(int userId);

    int getStickyNoteCountForUser(int userId);

    int getReminderCountTodayForUser(int userId);

    int getNextWeekReminderCountForUser(int userId);

    List<Note> getReminderNoteForTodayByUser(int userId);

    List<Note> getReminderNoteForNextWeekByUser(int userId);

    List<Note> findAllReminderNoteByUser(int userId);
}
