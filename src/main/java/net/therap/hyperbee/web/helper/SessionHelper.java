package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @author rayed
 * @since 11/24/16 12:12 PM
 */
@Component
public class SessionHelper {

    public void persistInSession(User user, HttpSession session) {
        AuthUser authUser = new AuthUser();
        authUser.setId(user.getId());
        authUser.setUsername(user.getUsername());
        authUser.setRoleList(user.getRoleList());

        session.setAttribute("authUser", authUser);
    }

    public AuthUser retrieveAuthUserFromSession(HttpSession session) {

        return (AuthUser) session.getAttribute("authUser");
    }

    public void invalidateSession(HttpSession session) {
        session.invalidate();
    }
}
