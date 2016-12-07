package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.HiveService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.web.helper.SessionHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author azim
 * @since 12/7/16
 */
@RunWith(MockitoJUnitRunner.class)
public class HiveControllerTest {

    @InjectMocks
    HiveController hiveController;

    @Mock
    HiveService hiveService;

    @Mock
    UserService userService;

    @Mock
    SessionHelper sessionHelper;

    @Test
    public void testUserNotInList() throws Exception {
        User user1 = new User();
        User user2 = new User();

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        Mockito.when(hiveService.getUserNotInList(1)).thenReturn(userList);
        assertEquals(hiveService.getUserNotInList(1), userList);
    }

    @Test
    public void testUserListToRemove() throws Exception {
        User user1 = new User();
        User user2 = new User();

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        Mockito.when(hiveService.getUserListToRemove(1)).thenReturn(userList);
        assertEquals(hiveService.getUserListToRemove(1), userList);
    }

    @Test
    public void testLatestNoticeList() throws Exception {
        Notice notice1 = new Notice();
        Notice notice2 = new Notice();

        List<Notice> noticeList = new ArrayList<>();
        noticeList.add(notice1);
        noticeList.add(notice2);

        Mockito.when( hiveService.getLatestNotice(noticeList)).thenReturn(noticeList);
        assertEquals(hiveService.getLatestNotice(noticeList).size(), 2);
    }
}
