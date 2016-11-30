package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.domain.User;

import java.util.List;

/**
 * @author bashir
 * @since 11/22/16
 */
public interface StickyNoteService {

    List<Note> findActiveNotesForUser(int userId);

    void saveNoteForUser(Note note, int userId);

    void markNoteAsInactiveForUser(int userId, int noteId);

    List<Note> findTopStickyNoteByUser(int userId);

    List<Note> findUpcomingReminderNoteByUser(int userId);

    int getRemainingReminderCountForUser(int userId);

    int getStickyNoteCountForUser(int userId);
}
