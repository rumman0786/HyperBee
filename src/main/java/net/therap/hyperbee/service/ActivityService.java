package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Activity;

/**
 * @author rayed
 * @since 11/28/16 10:23 AM
 */

public interface ActivityService {

    public void create(Activity activity);

    public Activity findById(int id);

    public Activity findByUserId(int userId);
}
