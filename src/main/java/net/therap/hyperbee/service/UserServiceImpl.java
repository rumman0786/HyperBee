package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.RoleDao;
import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.Role;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.utils.Utils;
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

    private static final int USER_ROLE_ID = 2;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private Utils utils;

    @Override
    @Transactional
    public User createUser(User user) {
        Role role = roleDao.findRole(USER_ROLE_ID);

        List<Role> roleList = new ArrayList<Role>();
        roleList.add(role);

        String hashMd5 = utils.hashMd5(user.getPassword());

        user.setRoleList(roleList);
        user.setPassword(hashMd5);

        return userDao.createUser(user);
    }

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
        String hashMd5 = utils.hashMd5(user.getPassword());
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
    public void inactivate(int userId) {
        userDao.inactivate(userId);
    }

    @Override
    @Transactional
    public void activate(int userId) {
        userDao.activate(userId);
    }

    @Override
    public int findByDisplayStatus(DisplayStatus status) {

        return userDao.findByDisplayStatus(status);
    }
}
