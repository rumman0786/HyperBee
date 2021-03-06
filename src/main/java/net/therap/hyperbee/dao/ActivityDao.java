package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Activity;

import java.util.List;

/**
 * @author rayed
 * @since 11/28/16 10:19 AM
 */
public interface ActivityDao {

    void create(Activity activity);

    List<Activity> findByUserId(int userId);
}
