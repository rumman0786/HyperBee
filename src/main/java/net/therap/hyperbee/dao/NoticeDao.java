package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Notice;

import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
public interface NoticeDao {

    void save(Notice notice);

    Notice findById(int noticeId);

    List<Notice> findAll();

    void delete(Notice notice);

    void delete(int noticeId);
}
