package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.enums.DisplayStatus;

import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
public interface BuzzService {

    Buzz saveBuzz(Buzz newBuzz);

    List<Buzz> getAllBuzz();

    Buzz getBuzzById(int buzzId);

    List<Buzz> getBuzzByStatus(DisplayStatus displayStatus);

    List<Buzz> getLatestBuzz();

    List<Buzz> getLatestPinnedBuzz();

    Buzz flagBuzz(Buzz buzzToFlag);

    Buzz deactivateBuzz(Buzz buzzToDeactivate);

    Buzz pinBuzz(Buzz buzzToPin);

    int getActiveCountByUser(int userId);

    int getPinnedCountByUser(int userId);

    int getFlaggedCountByUser(int userId);

    int getActiveCount();

    int getInactiveCount();

    int getPinnedCount();

    int getFlaggedCount();
}
