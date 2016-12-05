package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.NoticeDao;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.web.helper.NoticeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private NoticeHelper noticeHelper;

    @Autowired
    private ActivityService activityService;

    @Override
    @Transactional
    public void saveNotice(Notice notice, String archiveMessage) {
        noticeDao.saveOrUpdate(notice);
        activityService.archive(archiveMessage);
        noticeHelper.persistInSession();
    }

    @Override
    public Notice findNoticeById(int noticeId) {
        return noticeDao.findById(noticeId);
    }

    @Override
    public List<Notice> findAllNotice() {
        return noticeDao.findAll();
    }

    @Override
    public List<Notice> findLatestNotices(int range) {
        return noticeDao.findLatestNotices(range);
    }

    @Override
    @Transactional
    public void deleteNotice(Notice notice, String archiveMessage) {
        noticeDao.delete(notice);
        activityService.archive(archiveMessage);
        noticeHelper.persistInSession();
    }

    @Override
    @Transactional
    public void delete(int noticeId, String archiveMessage) {
        noticeDao.delete(noticeId);
        activityService.archive(archiveMessage);
        noticeHelper.persistInSession();
    }

    @Override
    public List<Notice> getNoticeListByHiveId(int hiveId, int range) {
        return noticeDao.getNoticeListByHiveId(hiveId, range);
    }
}
