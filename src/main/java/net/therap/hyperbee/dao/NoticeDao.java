package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Notice;

import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
public interface NoticeDao {
    /**
     * Save the Notice
     *
     * @param notice Notice to save.
     */
    void save(Notice notice);

    /**
     * Update the Notice
     *
     * @param notice Notice to update.
     */
    void update(Notice notice);

    /**
     * Find the Notice of given Id
     *
     * @param noticeId Notice to save.
     */
    Notice findById(Long noticeId);

    /**
     * Find list of all existing notice
     */
    List<Notice> findAll();

    /**
     * Delete the given Notice
     *
     * @param notice Notice to delete
     */
    void delete(Notice notice);

    /**
     * Delete the Notice with the given Id
     *
     * @param noticeId Id of the Notice to delete
     */
    void delete(Long noticeId);

}
