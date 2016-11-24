package net.therap.hyperbee.web.filter;

import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author rayed
 * @since 11/24/16 1:29 PM
 */

public class UrlFilter implements Filter {

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");

        if (authUser != null){
            chain.doFilter(request, response);

            return;
        }

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect("/login");
    }

    @Override
    public void destroy() {

    }
}
