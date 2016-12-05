package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * @author rayed
 * @since 12/1/16 4:42 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController = new UserController();

    @Mock
    private UserService userService;

    @Test
    public void testLoginGet() throws Exception {
        ModelMap modelMap = new ModelMap();
        String view = userController.login(modelMap);
        assertEquals("user/login", view);
    }

    @Test
    public void testLoginPost() throws Exception {
        User user = new User();
        when(userService.findByUsernameAndPassword(any(User.class))).thenReturn(user);

    }
}
