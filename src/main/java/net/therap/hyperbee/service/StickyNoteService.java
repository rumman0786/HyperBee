package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.domain.User;

import java.util.List;

/**
 * @author bashir
 * @since 11/22/16
 */
public interface StickyNoteService {

    public void createStickyNote(User user, Note note);
}
