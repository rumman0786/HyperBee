package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Notice;

import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
public interface NoticeService {
    /**
     * Save the Notice
     *
     * @param notice Notice to save.
     */
    void saveNotice(Notice notice);

    /**
     * Update the Notice
     *
     * @param notice Notice to update.
     */
    void updateNotice(Notice notice);

    /**
     * Find the Notice of given Id
     *
     * @param noticeId Notice to save.
     */
    Notice findNoticeById(Long noticeId);

    /**
     * Find list of all existing notice
     */
    List<Notice> findAllNotice();

    /**
     * Delete the given Notice
     *
     * @param notice Notice to delete
     */
    void deleteNotice(Notice notice);

    /**
     * Delete the Notice with the given Id
     *
     * @param noticeId Id of the Notice to delete
     */
    void delete(Long noticeId);

}
