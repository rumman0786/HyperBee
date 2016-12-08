package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;

import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:49 AM
 */
public interface UserDao {

    User findById(int id);

    User findByUsername(String username);

    User findByUsernameAndPassword(User user);

    User findByUsernameOrEmail(String username, String email);

    List<User> findAll();

    List<User> findActiveUser();

    int updateStatus(int userId, DisplayStatus status);

    int findByDisplayStatus(DisplayStatus status);

    User saveOrUpdate(User user);

    int findByRole(int role);
}
