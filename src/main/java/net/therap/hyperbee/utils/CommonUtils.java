package net.therap.hyperbee.utils;

import org.springframework.stereotype.Component;

/**
 * @author zoha
 * @since 11/22/16
 */
@Component
public class CommonUtils {

    public long getCurrentTimeMills() {
        return System.currentTimeMillis();
    }
}
