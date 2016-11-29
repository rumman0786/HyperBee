package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Notice;

import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
public interface NoticeService {

    void saveNotice(Notice notice);

    Notice findNoticeById(int noticeId);

    List<Notice> findAllNotice();

    void deleteNotice(Notice notice);

    void delete(int noticeId);
}
