package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Buzz;

import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
public interface BuzzService {

    Buzz saveBuzz(Buzz newBuzz);

    List<Buzz> getAllBuzz();

    Buzz getBuzzById(int buzzId);

    List<Buzz> getLatestBuzz();

    List<Buzz> getLatestPinnedBuzz();

    Buzz flagBuzz(Buzz buzzToFlag);

    Buzz deactivateBuzz(Buzz buzzToDeactivate);

    Buzz pinBuzz(Buzz buzzToPin);

    int getActiveCountByUser(int userId);

    List<Buzz> getActiveByUser(int userId);

    int getPinnedCountByUser(int userId);

    List<Buzz> getPinnedByUser(int userId);

    int getFlaggedCountByUser(int userId);

    List<Buzz> getFlaggedByUser(int userId);

    int getActiveCount();

    List<Buzz> getActive();

    int getPinnedCount();

    List<Buzz> getPinned();

    int getFlaggedCount();

    List<Buzz> getFlagged();

    int getInactiveCount();

    List<Buzz> getInactive();
}
