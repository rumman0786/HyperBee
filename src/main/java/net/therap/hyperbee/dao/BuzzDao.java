package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;

import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
public interface BuzzDao {

    public boolean create(Buzz newBuzz);

    public Buzz delete(Buzz buzzToDelete);

    public List<Buzz> retrieveAll();

    public Buzz retrieveById(int buzzId);

    public List<Buzz> retrieveByUser(int userId);

    public List<Buzz> retrieveByDisplayStatus(DisplayStatus displayStatus);

    public List<Buzz> retrieveLatest(int range);

    public Buzz update(Buzz buzzToUpdate);
}
