package net.therap.hyperbee.web.filter;

import net.therap.hyperbee.web.security.AuthUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static net.therap.hyperbee.utils.constant.Constant.*;
import static net.therap.hyperbee.utils.constant.Messages.*;
import static net.therap.hyperbee.utils.constant.Url.DONE_URL;

/**
 * @author zoha
 * @since 12/1/16
 */
public class BuzzHistoryFilter implements Filter {

    private static final Logger log = LogManager.getLogger(BuzzHistoryFilter.class);

    private static final String BUZZ_FILTER_INIT_LOG = "Buzz History Filter Initialized";
    private static final String BUZZ_FILTER_ACCESS_LOG = "Buzz History Filter Destroyed";
    private static final String BUZZ_FILTER_DESTROY_LOG = "Attempt of access by {}";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.trace(BUZZ_FILTER_INIT_LOG);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession(false);
        AuthUser authUser = (AuthUser) session.getAttribute(SESSION_KEY_AUTH_USER);

        if (authUser != null && authUser.isAdmin()) {
            chain.doFilter(request, response);
            return;
        }

        log.trace(BUZZ_FILTER_ACCESS_LOG, authUser.getUsername());

        session.setAttribute(DONE_PAGE_KEY_HTML_MESSAGE, BUZZ_ACCESS_DENIED);
        session.setAttribute(DONE_PAGE_KEY_HTML_TITLE, TITLE_ACCESS_DENIED);
        session.setAttribute(DONE_PAGE_KEY_HTML_MESSAGE_STYLE, FAILURE_HTML_CLASS);

        ((HttpServletResponse) response).sendRedirect(DONE_URL);
    }

    @Override
    public void destroy() {
        log.trace(BUZZ_FILTER_DESTROY_LOG);
    }
}
