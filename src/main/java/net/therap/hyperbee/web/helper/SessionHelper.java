package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.domain.User;
<<<<<<< HEAD
import net.therap.hyperbee.service.BuzzService;
=======
import net.therap.hyperbee.service.UserService;
>>>>>>> 9275ff091bbec4b6e12232458d395c2ad2f4c616
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
import java.util.Map;
>>>>>>> 9275ff091bbec4b6e12232458d395c2ad2f4c616

/**
 * @author rayed
 * @author bashir
 * @since 11/24/16 12:12 PM
 */
@Component
public class SessionHelper {

    @Autowired
<<<<<<< HEAD
    private BuzzService buzzService;
=======
    private UserService userService;
>>>>>>> 9275ff091bbec4b6e12232458d395c2ad2f4c616

    public void persistInSession(User user) {
        AuthUser authUser = new AuthUser();
        authUser.setId(user.getId());
        authUser.setUsername(user.getUsername());
        authUser.setRoleList(user.getRoleList());

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        session.setAttribute("authUser", authUser);
        session.setAttribute("buzzStats", generateBuzzStatsList(authUser.getId(), authUser.isAdmin()));
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

    public List<Integer> generateBuzzStatsList(int userId, boolean isAdmin) {
        List<Integer> buzzStatsList = new ArrayList<>();

        if (isAdmin) {
            buzzStatsList.add(buzzService.getActiveCount());
            buzzStatsList.add(buzzService.getInactiveCount());
            buzzStatsList.add(buzzService.getFlaggedCount());
            buzzStatsList.add(buzzService.getPinnedCount());
        } else {
            buzzStatsList.add(buzzService.getActiveCountByUser(userId));
            buzzStatsList.add(buzzService.getPinnedCountByUser(userId));
            buzzStatsList.add(buzzService.getFlaggedCountByUser(userId));
        }

        return buzzStatsList;
    }

    public HttpSession getHttpSession() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        return session;
    }
}
