package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Activity;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.helper.SessionHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author rayed
 * @since 12/5/16 10:13 AM
 */
@RunWith(MockitoJUnitRunner.class)
public class ActivityControllerTest {

    @InjectMocks
    private ActivityController activityController = new ActivityController();

    @Mock
    private ActivityService activityService;

    @Mock
    private UserService userService;

    @Mock
    private SessionHelper sessionHelper;

    @Mock
    private Model model;

    @Test
    public void testViewActivity() {
        List<Activity> activityList = new ArrayList<>();
        activityList.add(new Activity());
        activityList.add(new Activity());

        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());

        when(sessionHelper.getAuthUserIdFromSession()).thenReturn(1);
        when(activityService.findByUserId(anyInt())).thenReturn(activityList);
        when(userService.findAll()).thenReturn(userList);

        assertEquals("activity/log", activityController.viewActivity(model));
    }
}
