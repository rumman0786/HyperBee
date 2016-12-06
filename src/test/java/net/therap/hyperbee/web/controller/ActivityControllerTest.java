package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.helper.SessionHelper;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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


}
