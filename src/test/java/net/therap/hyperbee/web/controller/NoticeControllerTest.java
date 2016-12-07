package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.Role;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.domain.enums.RoleType;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.HiveService;
import net.therap.hyperbee.service.NoticeService;
import net.therap.hyperbee.service.UserService;
import net.therap.hyperbee.utils.constant.Url;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.security.AuthUser;
import net.therap.hyperbee.web.validator.NoticeValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

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

    @Mock
    Notice notice;

    @Mock
    BindingResult bindingResult;

    @Mock
    RedirectAttributes redirectAttributes;

    @Before
    public void setup() {
        initMocks(this);
        
        notice = getCreatedNotice();
        List<Role> roleList = getRoleList();

        AuthUser user = new AuthUser(1, "admin", roleList);

        when(sessionHelper.getAuthUserFromSession()).thenReturn(user);
        when(noticeController.addNotice(notice, bindingResult, redirectAttributes))
                .thenReturn("redirect:/notice");
    }

    @Test
    public void test_addNotice() {
        String url = noticeController.addNotice(notice, bindingResult,redirectAttributes);
        assertEquals("redirect:/notice", url);
    }

    private Notice getCreatedNotice() {
        User user = new User();
        user.setId(1);

        Hive hive = new Hive();
        hive.setId(1);

        List<Hive> hiveList = new ArrayList<>();
        hiveList.add(hive);

        notice = new Notice();
        notice.setId(1);
        notice.setUser(user);
        notice.setDateCreated(new GregorianCalendar());
        notice.setDateExpired(new GregorianCalendar());
        notice.setTitle("New notice");
        notice.setDescription("Description For Notice 1");
        notice.setDisplayStatus(DisplayStatus.ACTIVE);
        notice.setHiveList(hiveList);

        return notice;
    }

    private List<Role> getRoleList(){
        List<Role> roleList = new ArrayList<>();

        Role role1 = new Role();
        Role role2 = new Role();

        role1.setRoleType(RoleType.ADMIN);
        role2.setRoleType(RoleType.USER);

        roleList.add(role1);
        roleList.add(role2);

        return roleList;
    }
}
