package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.enums.DisplayStatus;

import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
public interface BuzzService {

    public Buzz saveBuzz(Buzz newBuzz);

    public List<Buzz> getAllBuzz();

    public Buzz getBuzzById(int buzzId);

    public List<Buzz> getBuzzByUser(String username);

    public List<Buzz> getBuzzByStatus(DisplayStatus displayStatus);

    List<Buzz> getLatestBuzz();

    public List<Buzz> getPinnedBuzz();

    public Buzz flagBuzz(Buzz buzzToFlag);

    public Buzz deactivateBuzz(Buzz buzzToDeactivate);

    public Buzz pinBuzz(Buzz buzzToPin);
}
