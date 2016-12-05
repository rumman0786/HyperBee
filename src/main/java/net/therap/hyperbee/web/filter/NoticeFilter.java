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
import static net.therap.hyperbee.utils.constant.Messages.FAILURE_HTML_CLASS;
import static net.therap.hyperbee.utils.constant.Messages.NOTICE_ACCESS_DENIED;
import static net.therap.hyperbee.utils.constant.Messages.TITLE_ACCESS_DENIED;
import static net.therap.hyperbee.utils.constant.Url.DONE_URL;

/**
 * @author rumman
 * @since 11/29/16
 */
public class NoticeFilter implements Filter {

    private static final Logger log = LogManager.getLogger(NoticeFilter.class);

    private static final String NOTICE_FILTER_INIT_MESSAGE = "Notice Filter Initialized";
    private static final String NOTICE_FILTER_DESTROY_MESSAGE = "Notice Filter Destroyed";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.trace(NOTICE_FILTER_INIT_MESSAGE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");

        if (authUser != null && authUser.isAdmin()) {
            chain.doFilter(request, response);
            return;
        }

        session.setAttribute(DONE_PAGE_KEY_HTML_TITLE, TITLE_ACCESS_DENIED);
        session.setAttribute(DONE_PAGE_KEY_HTML_MESSAGE, NOTICE_ACCESS_DENIED);
        session.setAttribute(DONE_PAGE_KEY_HTML_MESSAGE_STYLE, FAILURE_HTML_CLASS);

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect(DONE_URL);
    }

    @Override
    public void destroy() {
        log.trace(NOTICE_FILTER_DESTROY_MESSAGE);
    }
}
