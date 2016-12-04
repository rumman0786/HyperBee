package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Notice;

import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
public interface NoticeService {

    void saveNotice(Notice notice, String archiveMessage);

    Notice findNoticeById(int noticeId);

    List<Notice> findAllNotice();

    List<Notice> findLatestNotices(int range);

    void deleteNotice(Notice notice, String archiveMessage);

    void delete(int noticeId, String archiveMessage);

    List<Notice> getNoticeListByHiveId(int hiveId, int range);
}
