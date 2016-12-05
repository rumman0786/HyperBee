package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.utils.Utils;
import net.therap.hyperbee.web.helper.NoticeHelper;
import net.therap.hyperbee.web.helper.ReservationHelper;
import net.therap.hyperbee.web.helper.SessionHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static net.therap.hyperbee.utils.constant.Url.USER_DASHBOARD_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * @author rayed
 * @since 12/1/16 4:42 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private static final String LOGIN_VIEW = "user/login";

    @InjectMocks
    private UserController userController = new UserController();

    @Mock
    private UserService userService;

    @Mock
    private ActivityService activityService;

    @Mock
    private SessionHelper sessionHelper;

    @Mock
    private NoticeHelper noticeHelper;

    @Mock
    private ReservationHelper reservationHelper;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @Test
    public void testLoginGet() throws Exception {
        String view = userController.login(model);

        assertEquals(LOGIN_VIEW, view);
    }

    @Test
    public void testLoginPost() throws Exception {
        User user = new User();
        user.setDisplayStatus(DisplayStatus.ACTIVE);

        when(userService.findByUsernameAndPassword(any(User.class))).thenReturn(user);
        assertEquals(Utils.redirectTo(USER_DASHBOARD_URL), userController.loginUser(user, bindingResult));
        assertNotEquals(LOGIN_VIEW, userController.loginUser(user, bindingResult));
    }
}
