package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Notice;

import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
public interface NoticeDao {

    void saveOrUpdate(Notice notice);

    Notice findById(int noticeId);

    List<Notice> findAll();

    List<Notice> findLatestNotices(int range);

    void delete(Notice notice);

    void delete(int noticeId);
}
