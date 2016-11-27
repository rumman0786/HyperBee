package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.NoticeDao;
import net.therap.hyperbee.domain.Notice;
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

    @Override
    @Transactional
    public void saveNotice(Notice notice) {
        noticeDao.save(notice);
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
    @Transactional
    public void deleteNotice(Notice notice) {
        noticeDao.delete(notice);
    }

    @Override
    @Transactional
    public void delete(int noticeId) {
        noticeDao.delete(noticeId);
    }
}
