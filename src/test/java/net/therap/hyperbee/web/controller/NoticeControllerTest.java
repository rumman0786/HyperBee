package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.HiveService;
import net.therap.hyperbee.service.NoticeService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.validator.NoticeValidator;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * @author rumman
 * @since 12/1/16
 */
public class NoticeControllerTest {

    @InjectMocks
    private NoticeController noticeController;

    @Mock
    private NoticeService noticeService;

    @Mock
    private UserService userService;

    @Mock
    private HiveService hiveService;

    @Mock
    private ActivityService activityService;

    @Mock
    private SessionHelper sessionHelper;

    @Mock
    private NoticeValidator validator;

    @Before
    private void setup() {
        
    }
}
