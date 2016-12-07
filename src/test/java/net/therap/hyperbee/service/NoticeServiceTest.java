package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author rumman
 * @since 12/6/16
 */
public class NoticeServiceTest {

    @Mock
    private NoticeService noticeService;

    private Notice notice;

    @Before
    public void setup() {
        initMocks(this);

        notice = getCreatedNotice();
        doNothing().when(noticeService).saveNotice(notice);
    }

    @Test
    public void saveNoticeTest() {
        noticeService.saveNotice(notice);
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
}
