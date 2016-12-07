package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.RoleDao;
import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.Role;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.domain.enums.RoleType;
import net.therap.hyperbee.utils.Utils;
import net.therap.hyperbee.web.helper.NoticeHelper;
import net.therap.hyperbee.web.helper.ReservationHelper;
import net.therap.hyperbee.web.helper.SessionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:49 AM
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    private static final int USER_ROLE_ID = 2;
    private static final int ADMIN_ROLE_ID = 1;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private NoticeHelper noticeHelper;

    @Autowired
    private ReservationHelper reservationHelper;

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
    public List<User> searchByEntry(String entry) {

        return userDao.searchUserByEntry(entry);
    }

    @Override
    @Transactional
    public void inactivate(int userId, String username) {
        userDao.inactivate(userId);
        activityService.archive("Deactivated user [" + username + "]");
    }

    @Override
    @Transactional
    public void activate(int userId, String username) {
        userDao.activate(userId);
        activityService.archive("Activated user [" + username + "]");
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

        log.debug("\nUser at saveOrUpdate:\n" + user);

        User retrievedUser = userDao.saveOrUpdate(user);

        return retrievedUser;
    }

    @Override
    @Transactional
    public void changeRole(int userId, int role) {
        User user = userDao.findById(userId);

        if (role == ADMIN_ROLE_ID) {
            user.addRole(RoleType.ADMIN);
            activityService.archive("Promoted [" + user.getUsername() + "] from User to Admin");
        } else {
            user.removeRole(RoleType.ADMIN);
            activityService.archive("Demoted [" + user.getUsername() + "] from Admin to User");
        }

        userDao.saveOrUpdate(user);
    }
}
