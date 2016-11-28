package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Activity;

import java.util.List;

/**
 * @author rayed
 * @since 11/28/16 10:23 AM
 */

public interface ActivityService {

    public List<Activity> findByUserId(int userId);

    public void archive(String summary);
}
