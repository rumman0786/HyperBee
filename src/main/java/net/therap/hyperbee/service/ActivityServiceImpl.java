package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.ActivityDao;
import net.therap.hyperbee.domain.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rayed
 * @since 11/28/16 10:23 AM
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public void create(Activity activity) {
        activityDao.create(activity);
    }

    @Override
    public Activity findById(int id) {
        return null;
    }

    @Override
    public Activity findByUserId(int userId) {

        return activityDao.findByUserId(userId);
    }
}
