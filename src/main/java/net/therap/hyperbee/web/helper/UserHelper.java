package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rayed
 * @since 11/30/16 2:54 PM
 */
@Component
public class UserHelper {

    private Map<String, Integer> map;

    public UserHelper() {
        map = new HashMap<>();
    }

    public Map<String, Integer> generateMap(AuthUser authUser) {

        if (authUser.isAdmin()) {
            setUserStats();
        } else {

        }

        return map;
    }

    private void setUserStats() {
//        map.put("activeUsers", );
    }
}
