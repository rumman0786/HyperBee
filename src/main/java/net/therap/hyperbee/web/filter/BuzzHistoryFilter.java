package net.therap.hyperbee.web.filter;

import net.therap.hyperbee.web.security.AuthUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static net.therap.hyperbee.utils.constant.Constant.SESSION_KEY_AUTH_USER;
import static net.therap.hyperbee.utils.constant.Url.DONE_URL;
import static net.therap.hyperbee.utils.constant.Messages.BUZZ_ACCESS_DENIED;
import static net.therap.hyperbee.utils.constant.Messages.TITLE_ACCESS_DENIED;

/**
 * @author zoha
 * @since 12/1/16
 */
public class BuzzHistoryFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest)request).getSession();
        AuthUser authUser = (AuthUser) session.getAttribute(SESSION_KEY_AUTH_USER);

        if(authUser != null && authUser.isAdmin()) {
            chain.doFilter(request, response);
            return;
        }

        session.setAttribute("message", BUZZ_ACCESS_DENIED);
        session.setAttribute("htmlTitle", TITLE_ACCESS_DENIED);
        session.setAttribute("messageStyle", "alert alert-danger");

        ((HttpServletResponse)response).sendRedirect(DONE_URL);
    }

    @Override
    public void destroy() {

    }
}
