package net.therap.hyperbee.domain.enums;

import static net.therap.hyperbee.utils.constant.DomainConstant.NOTE_REMINDER;
import static net.therap.hyperbee.utils.constant.DomainConstant.NOTE_STICKY;

/**
 * @author bashir
 * @since 11/28/16
 */
public enum NoteType {

    STICKY(NOTE_STICKY),
    REMINDER(NOTE_REMINDER);

    String noteType;

    NoteType(String noteType) {

        this.noteType = noteType;
    }
}
