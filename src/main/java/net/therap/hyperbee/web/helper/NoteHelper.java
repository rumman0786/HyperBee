package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.domain.Note;
import org.springframework.stereotype.Component;

/**
 * @author bashir
 * @since 11/23/16
 */
@Component
public class NoteHelper {

    public Note getEmptyNote(){

        return new Note();
    }
}
