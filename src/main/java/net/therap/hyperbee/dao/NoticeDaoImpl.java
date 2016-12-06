package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
@Repository
public class NoticeDaoImpl implements NoticeDao {

    private static final String ACTIVE_NOTICE_LIST_QUERY = "SELECT n.* FROM notice n INNER JOIN notice_hive nh " +
            "ON n.id = nh.notice_id WHERE n.display_status= ? AND nh.hive_id = ? ORDER BY n.id DESC LIMIT ?;";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Notice saveOrUpdate(Notice notice) {
        if (notice.isNew()) {
            em.persist(notice);
            em.flush();
        } else {
            notice = em.merge(notice);
        }

        return notice;
    }

    @Override
    public Notice findById(int noticeId) {
        return em.find(Notice.class, noticeId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Notice> findAll() {
        return em.createNamedQuery("Notice.findAllNotice", Notice.class)
                .getResultList();
    }

    @Override
    public List<Notice> findLatestNotices(int range) {
        return em.createNamedQuery("Notice.findLatestNotices", Notice.class)
                .setMaxResults(range)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Notice notice) {
        delete(notice.getId());
    }

    @Override
    @Transactional
    public void delete(int noticeId) {
        Notice attachedNotice = em.getReference(Notice.class, noticeId);
        em.remove(attachedNotice);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Notice> getNoticeListByHiveId(int hiveId, int range) {
        Query query = em.createNativeQuery(ACTIVE_NOTICE_LIST_QUERY, Notice.class);
        query.setParameter(1, DisplayStatus.ACTIVE.getStatus());
        query.setParameter(2, hiveId);
        query.setParameter(3, range);

        return  query.getResultList();
    }
}