package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
@Repository
public class NoticeDaoImpl implements NoticeDao {

    private static final int LIST_INDEX_START = 0;

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
        Hive hive = em.find(Hive.class, hiveId);

        Hibernate.initialize(hive.getNoticeList());

        List<Notice> noticeList = new ArrayList<>(hive.getNoticeList());
        Iterator<Notice> iterator = noticeList.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().getDisplayStatus() != DisplayStatus.ACTIVE) {
                iterator.remove();
            }
        }

        if (noticeList.size() >= range){
            return noticeList.subList(LIST_INDEX_START, range);
        }

        return noticeList;
    }
}