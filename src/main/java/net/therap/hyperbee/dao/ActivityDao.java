package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Activity;

/**
 * @author rayed
 * @since 11/28/16 10:19 AM
 */
public interface ActivityDao {

    public void create(Activity activity);

    public Activity findById(int id);

    public Activity findByUserId(int userId);
}
