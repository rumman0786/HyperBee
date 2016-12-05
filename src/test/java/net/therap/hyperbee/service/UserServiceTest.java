package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.RoleDao;
import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.Role;
import net.therap.hyperbee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * @author rayed
 * @since 12/5/16 10:32 AM
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final int USER_ID = 1;

    private static final String USER_NAME = "rayed";

    private static final String USER_EMAIL = "rayed@gmail.com";

    private static final String USER_PASSWORD = "123";

    private static final int USER_ROLE_ID = 2;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @Test
    public void testFindById() {
        User user = new User();

        when(userDao.findById(anyInt())).thenReturn(user);
        assertEquals(userService.findById(USER_ID), user);
    }

    @Test
    public void testFindByUsernameOrEmail() {
        User user = new User();

        when(userDao.findByUsernameOrEmail(anyString(), anyString())).thenReturn(user);
        assertEquals(userService.findByUsernameOrEmail(USER_NAME, USER_EMAIL), user);
    }

    @Test
    public void testFindAll() {
        List<User> userList = Arrays.asList(new User(), new User());

        when(userDao.findAll()).thenReturn(userList);
        assertEquals(userService.findAll(), userList);
        assertEquals(userList.size(), 2);
    }

    @Test
    public void testFindByUsernameAndPassword() {
        User user = new User();
        user.setPassword(USER_PASSWORD);

        when(userDao.findByUsernameAndPassword(any(User.class))).thenReturn(user);
        assertEquals(userService.findByUsernameAndPassword(user), user);
    }

    @Test
    public void testCreateUser() {
        Role role = new Role();
        role.setId(USER_ROLE_ID);

        User user = new User();
        user.setPassword(USER_PASSWORD);

        when(roleDao.findRole(anyInt())).thenReturn(role);
        when(userDao.createUser(any(User.class))).thenReturn(user);
        assertEquals(userService.createUser(user), user);
    }
}
