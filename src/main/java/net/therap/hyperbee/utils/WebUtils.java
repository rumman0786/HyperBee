package net.therap.hyperbee.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author rumman
 * @since 12/5/16
 */
public class WebUtils {

    public static void setSessionAttribute(String sessionKey, Object value) {
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest().getSession().setAttribute(sessionKey, value);
    }
}
