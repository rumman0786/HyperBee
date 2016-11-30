package net.therap.hyperbee.web.helper;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rayed
 * @since 11/30/16 2:54 PM
 */
@Component
public class UserHelper {


    Map<String, Integer> map;

    public UserHelper() {
        map = new HashMap<>();
    }

    public Map<String, Integer> generateMap(AuthUser authUser, List<User> users) {

        if (authUser.isAdmin()) {
            setUserStats(users);

        } else {

        }

        return map;
    }

    private void setUserStats(List<User> users) {
//        System.out.println(users);

        int activeUsers = -1;
        int deactivatedUsers = 0;

        for (User user : users) {
            if (user.getDisplayStatus() == DisplayStatus.ACTIVE){
                activeUsers++;
            } else {
                deactivatedUsers++;
            }
        }

        map.put("activeUsers", activeUsers);
        map.put("deactivatedUsers", deactivatedUsers);
    }
}
