package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Activity;

import java.util.List;

/**
 * @author rayed
 * @since 11/28/16 10:23 AM
 */

public interface ActivityService {

    List<Activity> findByUserId(int userId);

    void archive(String summary);
}
