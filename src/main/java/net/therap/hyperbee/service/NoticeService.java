package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Notice;

import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
public interface NoticeService {

    void saveNoticeAndArchive(Notice notice, String archiveMessage);

    void saveNotice(Notice notice);

    Notice findNoticeById(int noticeId);

    List<Notice> findAllNotice();

    List<Notice> findLatestNotices(int range);

    void deleteNoticeAndArchive(int noticeId, String archiveMessage);

    void deleteNotice(Notice notice);

    void delete(int noticeId);

    List<Notice> getNoticeListByHiveId(int hiveId, int range);
}
