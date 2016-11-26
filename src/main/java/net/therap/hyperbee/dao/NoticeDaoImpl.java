package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Notice;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
@Repository
public class NoticeDaoImpl implements NoticeDao {

    private static final String NOTICE_ALL_QUERY = "FROM Notice";

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Notice notice) {

        if (notice.getId() == 0) {
            em.persist(notice);
        } else {
            em.merge(notice);
        }
    }

    @Override
    public Notice findById(int noticeId) {
        return em.find(Notice.class, noticeId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Notice> findAll() {
        return em.createQuery(NOTICE_ALL_QUERY).getResultList();
    }

    @Override
    public void delete(Notice notice) {
        delete(notice.getId());
    }

    @Override
    public void delete(int noticeId) {
        Notice attachedDish = em.getReference(Notice.class, noticeId);
        em.remove(attachedDish);
    }
}
