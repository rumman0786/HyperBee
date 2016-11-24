package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.web.security.AuthUser;

import javax.servlet.http.HttpSession;

/**
 * @author rayed
 * @since 11/24/16 12:12 PM
 */

public class SessionHelper {

    public static void persistInSession(User user, HttpSession session) {
        AuthUser authUser = new AuthUser();
        authUser.setId(user.getId());
        authUser.setUsername(user.getUsername());
        authUser.setRoleList(user.getRoleList());

        session.setAttribute("authUser", authUser);
    }

    public static AuthUser retrieveAuthUserFromSession(HttpSession session) {

        return (AuthUser) session.getAttribute("authUser");
    }

    public static void invalidateSession(HttpSession session) {
        session.invalidate();
    }
}
