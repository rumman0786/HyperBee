package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * @author rayed
 * @since 12/1/16 4:42 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void testLoginController() throws Exception {
        User user = new User();
    }
}
