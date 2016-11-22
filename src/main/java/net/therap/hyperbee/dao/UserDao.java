package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.User;

import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:49 AM
 */

public interface UserDao {

    public void createUser(User user);

    public User findById(int id);

    public User findByUsername(String username);

    public List<User> findAll();

    public void updateUser(User user);

    public void deleteUser(int id);
}
