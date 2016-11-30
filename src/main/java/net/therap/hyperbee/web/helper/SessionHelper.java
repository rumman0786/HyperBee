package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.BuzzService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author rayed
 * @author bashir
 * @since 11/24/16 12:12 PM
 */
@Component
public class SessionHelper {

    @Autowired
    private BuzzService buzzService;

    @Autowired
    private UserService userService;

    public void persistInSession(User user) {
        AuthUser authUser = new AuthUser();
        authUser.setId(user.getId());
        authUser.setUsername(user.getUsername());
        authUser.setRoleList(user.getRoleList());

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        session.setAttribute("authUser", authUser);
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

    public void setStatInSession() {
        AuthUser authUserFromSession = getAuthUserFromSession();

        if (authUserFromSession.isAdmin()){
            int activeUser  = userService.findByDisplayStatus(DisplayStatus.ACTIVE);
            int inactiveUser  = userService.findByDisplayStatus(DisplayStatus.INACTIVE);

            int activeBuzz = buzzService.getActiveCount();
            int inactiveBuzz = buzzService.getInactiveCount();
            int flaggedBuzz = buzzService.getFlaggedCount();
            int pinnedBuzz = buzzService.getPinnedCount();

            setStat("activeUsers", activeUser);
            setStat("inactiveUsers", inactiveUser);

            setStat("activeBuzz", activeBuzz);
            setStat("inactiveBuzz", inactiveBuzz);
            setStat("flaggedBuzz", flaggedBuzz);
            setStat("pinnedBuzz", pinnedBuzz);
        } else {
            int activeBuzz = buzzService.getActiveCountByUser(authUserFromSession.getId());
            int flaggedBuzz = buzzService.getFlaggedCountByUser(authUserFromSession.getId());
            int pinnedBuzz = buzzService.getPinnedCountByUser(authUserFromSession.getId());

            setStat("activeBuzz", activeBuzz);
            setStat("flaggedBuzz", flaggedBuzz);
            setStat("pinnedBuzz", pinnedBuzz);
        }
    }

    public void setStat(String key, int value) {
        HttpSession httpSession = getHttpSession();
        httpSession.setAttribute(key, value);
    }

    public int getStat(String key) {
        return (Integer) getHttpSession().getAttribute(key);
    }

    public void decrementSessionAttribute(String key, int increment) {
        int count = getStat(key);
        setStat(key, count - increment);
    }

    public void incrementSessionAttribute(String key, int increment) {
        int count = getStat(key);
        setStat(key, count + increment);
    }
}
