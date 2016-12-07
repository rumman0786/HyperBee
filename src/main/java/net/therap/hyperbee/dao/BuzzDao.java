package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.User;

import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
public interface BuzzDao {

    Buzz saveOrUpdate(Buzz newBuzz);

    List<Buzz> getAll();

    Buzz getById(int buzzId);

    List<Buzz> getLatest(int range);

    List<Buzz> getLatestPinnedBuzz(int range);

    int getActiveCountByUser(User user);

    List<Buzz> getActiveByUser(User user);

    int getPinnedCountByUser(User user);

    List<Buzz> getPinnedByUser(User user);

    int getFlaggedCountByUser(User user);

    List<Buzz> getFlaggedByUser(User user);

    int getActiveCount();

    List<Buzz> getActive();

    int getPinnedCount();

    List<Buzz> getPinned();

    int getFlaggedCount();

    List<Buzz> getFlagged();

    int getInactiveCount();

    List<Buzz> getInactive();
}
