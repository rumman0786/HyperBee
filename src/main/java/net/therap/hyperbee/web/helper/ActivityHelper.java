package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.domain.Activity;
import net.therap.hyperbee.domain.User;
import org.springframework.stereotype.Component;

/**
 * @author rayed
 * @since 11/28/16 10:41 AM
 */
@Component
public class ActivityHelper {

    public Activity createActivity(User retrievedUser) {
        Activity activity = new Activity();
        activity.setUser(retrievedUser);
        return activity;
    }

    public void setSummary(Activity activity, String summary) {
        activity.setSummary(summary);
    }
}
