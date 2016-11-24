package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.RoleDao;
import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.Role;
import net.therap.hyperbee.domain.User;
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

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public void createUser(User user) {
        Role role = roleDao.findRole(2);

        List<Role> roleList = new ArrayList<Role>();
        roleList.add(role);

        user.setRoleList(roleList);
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
    public User findByUsernameAndPassword(User user) {
        return userDao.findByUsernameAndPassword(user);
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
