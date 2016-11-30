package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.NoteType;
import net.therap.hyperbee.service.StickyNoteService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.security.AuthUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static net.therap.hyperbee.utils.constant.DomainConstant.*;

/**
 * @author rayed
 * @author bashir
 * @since 11/24/16 12:12 PM
 */
@Component
public class SessionHelper {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StickyNoteService noteService;

    public void persistInSession(User user) {
        AuthUser authUser = new AuthUser();
        authUser.setId(user.getId());
        authUser.setUsername(user.getUsername());
        authUser.setRoleList(user.getRoleList());

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        session.setAttribute("authUser", authUser);
        initializeNoteStatForUser(authUser.getId());
    }

    public void persistInSession(Map<String, Integer> map) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        session.setAttribute("statsMap", map);
    }

    public void persistInSession(String key, int count) {
        HttpSession session = getHttpSession();
        Map<String, Integer> statsMap = (Map<String, Integer>) session.getAttribute("statsMap");

        statsMap.put(key, count);
        session.setAttribute("statsMap", statsMap);
    }

    public AuthUser getAuthUserFromSession() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpSession session = servletRequestAttributes.getRequest().getSession();

        return (AuthUser) session.getAttribute("authUser");
    }

    public void invalidateSession() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpSession session = servletRequestAttributes.getRequest().getSession();
        session.invalidate();
    }


    public int getUserIdFromSession() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpSession session = servletRequestAttributes.getRequest().getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");

        return authUser.getId();
    }

    public HttpSession getHttpSession() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        return session;
    }

    public void initializeNoteStatForUser(int userId) {
        int stickyNoteCount = noteService.getStickyNoteCountForUser(userId);
        int reminderCount = noteService.getRemainingReminderCountForUser(userId);

        HttpSession session = getHttpSession();
        session.setAttribute(SESSION_VARIABLE_STICKY_COUNT, stickyNoteCount);
        session.setAttribute(SESSION_VARIABLE_REMINDER_COUNT, reminderCount);
    }

    public void incrementNoteCountByOne(NoteType noteType) {
        if (noteType == NoteType.STICKY) {
            int stickyCount = (int) getHttpSession().getAttribute(SESSION_VARIABLE_STICKY_COUNT);
            getHttpSession().setAttribute(SESSION_VARIABLE_STICKY_COUNT, ++stickyCount);
        } else if (noteType == NoteType.REMINDER) {
            int reminderCount = (int) getHttpSession().getAttribute(SESSION_VARIABLE_REMINDER_COUNT);
            getHttpSession().setAttribute(SESSION_VARIABLE_REMINDER_COUNT, ++reminderCount);
        }
    }

    public void decrementNoteCountByOne(String noteType) {
        if (noteType.equals(NOTE_STICKY)) {
            int stickyCount = (int) getHttpSession().getAttribute(SESSION_VARIABLE_STICKY_COUNT);
            getHttpSession().setAttribute(SESSION_VARIABLE_STICKY_COUNT, --stickyCount);
        } else if (noteType.equals(NOTE_REMINDER)) {
            int reminderCount = (int) getHttpSession().getAttribute(SESSION_VARIABLE_REMINDER_COUNT);
            getHttpSession().setAttribute(SESSION_VARIABLE_REMINDER_COUNT, --reminderCount);
        }
    }
}
