package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.enums.DisplayStatus;

import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
public interface BuzzDao {

    public Buzz saveOrUpdate(Buzz newBuzz);

    public Buzz delete(Buzz buzzToDelete);

    public List<Buzz> getAll();

    public Buzz getById(int buzzId);

    public List<Buzz> getByUser(int userId);

    public List<Buzz> getByDisplayStatus(DisplayStatus displayStatus);

    public List<Buzz> getLatest(int range);

    List<Buzz> getPinnedBuzz(int range);
}
