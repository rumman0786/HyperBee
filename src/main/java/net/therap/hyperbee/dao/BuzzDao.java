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

    Buzz saveOrUpdate(Buzz newBuzz);

    List<Buzz> getAll();

    Buzz getById(int buzzId);

    List<Buzz> getByDisplayStatus(DisplayStatus displayStatus);

    List<Buzz> getLatest(int range);

    List<Buzz> getLatestPinnedBuzz(int range);

    int getActiveCountByUser(User user);

    int getPinnedCountByUser(User user);

    int getFlaggedCountByUser(User user);

    int getActiveCount();

    int getPinnedCount();

    int getFlaggedCount();

    int getInactiveCount();
}
