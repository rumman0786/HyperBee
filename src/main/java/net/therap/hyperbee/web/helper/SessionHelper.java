package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.NoteService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.security.AuthUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

import static net.therap.hyperbee.utils.constant.Constant.*;

/**
 * @author rayed
 * @author bashir
 * @author zoha
 * @since 11/24/16 12:12 PM
 */
@Component
public class SessionHelper {

    private static final Logger log = LogManager.getLogger(SessionHelper.class);

    @Autowired
    private BuzzService buzzService;

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    public AuthUser getAuthUserFromSession() {

        return (AuthUser) getSessionAttribute(SESSION_KEY_AUTH_USER);
    }

    public int getUserIdFromSession() {
        AuthUser authUser = (AuthUser) getSessionAttribute("authUser");

        return authUser.getId();
    }

    public HttpSession getHttpSession() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        return servletRequestAttributes.getRequest().getSession();
    }


    public void setSessionAttribute(String key, Object value) {
        getHttpSession().setAttribute(key, value);
    }

    public Object getSessionAttribute(String key) {
        return getHttpSession().getAttribute(key);
    }

    public void invalidateSession() {
        getHttpSession().invalidate();
    }

    public void initializeNoteStatForUser(int userId) {
        int stickyNoteCount = noteService.getStickyNoteCountForUser(userId);
        int reminderCount = noteService.getRemainingReminderCountForUser(userId);
        int reminderCountForToday = noteService.getReminderCountTodayForUser(userId);
        int reminderCountForNextWeek = noteService.getNextWeekReminderCountForUser(userId);

        setSessionAttribute(SESSION_KEY_STICKY_COUNT, stickyNoteCount);
        setSessionAttribute(SESSION_KEY_REMINDER_COUNT, reminderCount);
        setSessionAttribute(SESSION_KEY_REMINDER_COUNT_TODAY, reminderCountForToday);
        setSessionAttribute(SESSION_KEY_REMINDER_COUNT_NEXT_WEEK, reminderCountForNextWeek);
    }

    public void setStatInSession() {
        AuthUser authUserFromSession = getAuthUserFromSession();

        if (authUserFromSession.isAdmin()) {
            int activeUser = userService.findByDisplayStatus(DisplayStatus.ACTIVE);
            int inactiveUser = userService.findByDisplayStatus(DisplayStatus.INACTIVE);

            int activeBuzz = buzzService.getActiveCount();
            int inactiveBuzz = buzzService.getInactiveCount();
            int flaggedBuzz = buzzService.getFlaggedCount();
            int pinnedBuzz = buzzService.getPinnedCount();

            setSessionAttribute(SESSION_KEY_ACTIVE_USERS, activeUser - USER_ACTIVATION_COUNT);
            setSessionAttribute(SESSION_KEY_INACTIVE_USERS, inactiveUser);

            setSessionAttribute(SESSION_VARIABLE_ACTIVE_BUZZ_COUNT, activeBuzz);
            setSessionAttribute(SESSION_VARIABLE_INACTIVE_BUZZ_COUNT, inactiveBuzz);
            setSessionAttribute(SESSION_VARIABLE_FLAGGED_BUZZ_COUNT, flaggedBuzz);
            setSessionAttribute(SESSION_VARIABLE_PINNED_BUZZ_COUNT, pinnedBuzz);
        } else {
            int activeBuzz = buzzService.getActiveCountByUser(authUserFromSession.getId());
            int flaggedBuzz = buzzService.getFlaggedCountByUser(authUserFromSession.getId());
            int pinnedBuzz = buzzService.getPinnedCountByUser(authUserFromSession.getId());

            setSessionAttribute(SESSION_VARIABLE_ACTIVE_BUZZ_COUNT, activeBuzz);
            setSessionAttribute(SESSION_VARIABLE_FLAGGED_BUZZ_COUNT, flaggedBuzz);
            setSessionAttribute(SESSION_VARIABLE_PINNED_BUZZ_COUNT, pinnedBuzz);
        }
    }


    public void decrementSessionAttribute(String key, int increment) {
        int count = (int) getSessionAttribute(key);
        setSessionAttribute(key, count - increment);
    }

    public void incrementSessionAttribute(String key, int increment) {
        int count = (int) getSessionAttribute(key);
        setSessionAttribute(key, count + increment);
    }
}
