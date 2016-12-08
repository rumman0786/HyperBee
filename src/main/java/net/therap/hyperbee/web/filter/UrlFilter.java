package net.therap.hyperbee.web.filter;

import net.therap.hyperbee.web.security.AuthUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static net.therap.hyperbee.utils.constant.Constant.SESSION_KEY_AUTH_USER;
import static net.therap.hyperbee.utils.constant.Url.*;

/**
 * @author rayed
 * @since 11/24/16 1:29 PM
 */
public class UrlFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/login", "/logout", "/signUp")));

    private static final Set<String> RESOURCES = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(RESOURCE_STYLE, RESOURCE_SCRIPT, RESOURCE_FONT, RESOURCE_IMAGES)));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String requestURI = httpServletRequest.getRequestURI();
        String servletPath = httpServletRequest.getServletPath();

        if (ALLOWED_PATHS.contains(requestURI) || containsResource(servletPath)){
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = ((HttpServletRequest) request).getSession();
        AuthUser authUser = (AuthUser) session.getAttribute(SESSION_KEY_AUTH_USER);

        if (authUser != null){
            chain.doFilter(request, response);
            return;
        }

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect(LOGIN_URL);
    }

    private boolean containsResource(String servletPath) {

        for (String RESOURCE : RESOURCES) {
            if (servletPath.startsWith(RESOURCE)) {

                return true;
            }
        }

        return false;
    }

    @Override
    public void destroy() {

    }
}
