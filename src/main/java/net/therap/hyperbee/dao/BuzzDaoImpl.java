package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */

@Repository
public class BuzzDaoImpl implements BuzzDao {

    private final String QUERY_GET_BY_STATUS = "SELECT b FROM Buzz b WHERE b.displayStatus = :displayStatus";
    private final String QUERY_GET_LATEST = "SELECT b FROM Buzz b WHERE b.displayStatus = :displayStatus " +
                                                                                          "ORDER BY b.id DESC";
    private final String QUERY_GET_BY_USER = "SELECT b FROM Buzz b WHERE b.userId = :userId";


    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public boolean save(Buzz newBuzz) {
        em.persist(newBuzz);
        return true;
    }

    @Override
    public List<Buzz> getAll() {
        return em.createQuery(QUERY_GET_BY_STATUS)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .getResultList();
    }

    @Override
    public Buzz getById(int buzzId) {
        return em.find(Buzz.class, buzzId);
    }

    @Override
    public List<Buzz> getByUser(int userId) {
        return em.createQuery(QUERY_GET_BY_USER)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Buzz> getByDisplayStatus(DisplayStatus displayStatus) {
        return em.createQuery(QUERY_GET_BY_STATUS)
                .setParameter("displayStatus", displayStatus.getStatus())
                .getResultList();
    }

    @Override
    public List<Buzz> getLatest(int range) {
        return em.createQuery(QUERY_GET_LATEST)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .setMaxResults(range)
                .getResultList();
    }

    @Override
    public Buzz modify(Buzz buzzToUpdate) {
        return em.merge(buzzToUpdate);
    }

    @Override
    @Transactional
    public Buzz delete(Buzz buzzToDelete) {
        return em.merge(buzzToDelete);
    }
}