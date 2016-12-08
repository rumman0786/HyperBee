package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.RoleDao;
import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.Role;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.domain.enums.RoleType;
import net.therap.hyperbee.utils.Utils;
import net.therap.hyperbee.web.helper.SessionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static net.therap.hyperbee.utils.constant.Constant.*;
import static net.therap.hyperbee.utils.constant.Messages.*;

/**
 * @author rayed
 * @since 11/22/16 10:49 AM
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    private static final int USER_ROLE_ID = 2;
    private static final int ADMIN_ROLE_ID = 1;

    private static final int INCREMENT = 1;
    private static final int DECREMENT = 1;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public User findById(int id) {

        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String username) {

        return userDao.findByUsername(username);
    }

    @Override
    public User findByUsernameOrEmail(String username, String email) {

        return userDao.findByUsernameOrEmail(username, email);
    }

    @Override
    public User findByUsernameAndPassword(User user) {
        String hashMd5 = Utils.hashMd5(user.getPassword());
        user.setPassword(hashMd5);

        return userDao.findByUsernameAndPassword(user);
    }

    @Override
    public List<User> findAll() {

        return userDao.findAll();
    }

    @Override
    public List<User> findActiveUsers() {

        return userDao.findActiveUser();
    }

    @Override
    @Transactional
    public void updateStatus(int userId, String username, DisplayStatus status) {
        int i = userDao.updateStatus(userId, status);

        if (i > 0) {
            int activeUsers = (int) sessionHelper.getSessionAttribute(SESSION_KEY_ACTIVE_USERS);
            int inactiveUsers = (int) sessionHelper.getSessionAttribute(SESSION_KEY_INACTIVE_USERS);

            if (status == DisplayStatus.ACTIVE){
                sessionHelper.setSessionAttribute(SESSION_KEY_ACTIVE_USERS, activeUsers + INCREMENT);
                sessionHelper.setSessionAttribute(SESSION_KEY_INACTIVE_USERS, inactiveUsers - DECREMENT);

                activityService.archive(USER_ACTIVATED + username);
            } else {
                sessionHelper.setSessionAttribute(SESSION_KEY_ACTIVE_USERS, activeUsers - DECREMENT);
                sessionHelper.setSessionAttribute(SESSION_KEY_INACTIVE_USERS, inactiveUsers + INCREMENT);

                activityService.archive(USER_DEACTIVATED + username);
            }
        }
    }

    @Override
    public int findByDisplayStatus(DisplayStatus status) {

        return userDao.findByDisplayStatus(status);
    }

    @Override
    @Transactional
    public User saveOrUpdate(User user) {
        if (user.isNew()) {
            Role role = roleDao.findRole(USER_ROLE_ID);

            List<Role> roleList = new ArrayList<Role>();
            roleList.add(role);

            user.setRoleList(roleList);
        }

        String hashMd5 = Utils.hashMd5(user.getPassword());
        user.setPassword(hashMd5);

        User retrievedUser = userDao.saveOrUpdate(user);

        sessionHelper.setSessionAttribute(SESSION_KEY_AUTH_USER, retrievedUser.getAuthUser());

        return retrievedUser;
    }

    @Override
    @Transactional
    public void updateRole(int userId, int roleId) {
        User user = userDao.findById(userId);
        Role role = roleDao.findRole(ADMIN_ROLE_ID);

        int activeUsers = (int) sessionHelper.getSessionAttribute(SESSION_KEY_ACTIVE_USERS);
        int adminUsers = (int) sessionHelper.getSessionAttribute(SESSION_KEY_ADMIN_USERS);

        if (roleId == ADMIN_ROLE_ID) {
            user.getRoleList().add(role);

            sessionHelper.setSessionAttribute(SESSION_KEY_ACTIVE_USERS, activeUsers - DECREMENT);
            sessionHelper.setSessionAttribute(SESSION_KEY_ADMIN_USERS, adminUsers + INCREMENT);

            activityService.archive(user.getUsername() + ROLE_CHANGED_TO_ADMIN);
        } else {
            user.getRoleList().remove(role);

            sessionHelper.setSessionAttribute(SESSION_KEY_ACTIVE_USERS, activeUsers + INCREMENT);
            sessionHelper.setSessionAttribute(SESSION_KEY_ADMIN_USERS, adminUsers - DECREMENT);

            activityService.archive(user.getUsername() + ROLE_CHANGED_TO_USER);
        }

        userDao.saveOrUpdate(user);
    }

    @Override
    public int findByRole(RoleType type) {
        int count = 0;

        if (type == RoleType.ADMIN){
            count = userDao.findByRole(ADMIN_ROLE_ID);
        } else {
            count = userDao.findByRole(USER_ROLE_ID);
        }

        return count;
    }
}
