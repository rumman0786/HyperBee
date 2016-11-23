package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.enums.DisplayStatus;

import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
public interface BuzzService {

    public boolean createBuzz(Buzz newBuzz);

    public boolean createPinnedBuzz(Buzz newBuzz);

    public List<Buzz> retrieveAllBuzz();

    public Buzz retrieveBuzzById(int buzzId);

    public List<Buzz> retrieveBuzzByUser(String username);

    public List<Buzz> retrieveBuzzByStatus(DisplayStatus displayStatus);

    List<Buzz> retrieveLatestBuzz();

    public Buzz updateBuzz(Buzz buzzToUpdate);

    public Buzz deleteBuzz(Buzz buzzToDelete);
}
