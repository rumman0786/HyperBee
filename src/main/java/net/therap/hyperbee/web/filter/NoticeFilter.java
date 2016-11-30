package net.therap.hyperbee.web.filter;

import net.therap.hyperbee.web.security.AuthUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static net.therap.hyperbee.utils.constant.Url.ACCESS_DENIED_URL;

/**
 * @author rumman
 * @since 11/29/16
 */
public class NoticeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");

        if (authUser != null && authUser.isAdmin()) {
            chain.doFilter(request, response);
        }

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect(ACCESS_DENIED_URL);
    }

    @Override
    public void destroy() {

    }
}
