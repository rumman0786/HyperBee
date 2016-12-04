package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;

import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:44 AM
 */
public interface UserService {

    User createUser(User user);

    User findById(int id);

    User findByUsername(String username);

    User findByUsernameOrEmail(String username, String email);

    User findByUsernameAndPassword(User user);

    List<User> findAll();

    List<User> findActiveUsers();

    List<User> searchByEntry(String entry);

    void inactivate(int userId);

    void activate(int userId);

    int findByDisplayStatus(DisplayStatus status);
}
