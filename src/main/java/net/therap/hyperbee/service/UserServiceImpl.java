package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:49 AM
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void createUser(User user) {
        userDao.createUser(user);
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
    public AuthUser findByUsernameAndPassword(User user) {
        AuthUser authUser = null;

        User retrievedUser = userDao.findByUsernameAndPassword(user);

        if (retrievedUser != null) {
            authUser = new AuthUser();
            authUser.setId(retrievedUser.getId());
            authUser.setUsername(retrievedUser.getUsername());
            authUser.setRoleList(retrievedUser.getRoleList());
        }

        return authUser;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void updateUser(User user) {

    }

    @Override
    @Transactional
    public void deleteUser(int id) {

    }
}
