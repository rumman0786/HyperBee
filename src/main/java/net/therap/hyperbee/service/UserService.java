package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.domain.enums.RoleType;

import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:44 AM
 */
public interface UserService {

    User findById(int id);

    User findByUsername(String username);

    User findByUsernameOrEmail(String username, String email);

    User findByUsernameAndPassword(User user);

    List<User> findAll();

    List<User> findActiveUsers();

    int findByDisplayStatus(DisplayStatus status);

    User saveOrUpdate(User user);

    void updateRole(int userId, int role);

    int findByRole(RoleType admin);

    void updateStatus(int userId, String username, DisplayStatus status);
}
