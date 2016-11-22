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

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Notice notice) {
        entityManager.persist(notice);
    }

    @Override
    public void update(Notice notice) {
        entityManager.merge(notice);
    }

    @Override
    public Notice findById(Long noticeId) {
        return entityManager.find(Notice.class, noticeId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Notice> findAll() {
        return entityManager.createQuery("from Notice").getResultList();
    }

    @Override
    public void delete(Notice notice) {
        delete((long) notice.getId());
    }

    @Override
    public void delete(Long noticeId) {
        Notice attachedDish = entityManager.getReference(Notice.class, noticeId);
        entityManager.remove(attachedDish);
    }
}
