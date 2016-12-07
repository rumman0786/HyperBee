package net.therap.hyperbee.dao;

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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author rumman
 * @since 12/7/16
 */
public class NoticeDaoTest {

    @Mock
    private NoticeDao noticeDao;

    private Notice notice;

    @Before
    public void setup() {
        initMocks(this);

        notice = getCreatedNotice();

        when(noticeDao.saveOrUpdate(notice)).thenReturn(getCheckNotice());
    }

    @Test
    public void saveOrUpdateTest() {
        int expectedOp = notice.getId();
        int passedOp = noticeDao.saveOrUpdate(notice).getId();

        assertEquals(expectedOp, passedOp);
    }

    private Notice getCheckNotice() {
        Notice notice = new Notice();
        notice.setId(1);

        return notice;
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
