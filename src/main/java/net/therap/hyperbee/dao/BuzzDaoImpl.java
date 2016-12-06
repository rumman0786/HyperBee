package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import net.therap.hyperbee.utils.Utils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
@Repository
public class BuzzDaoImpl implements BuzzDao {

    private final String QUERY_GET_LATEST = "SELECT b FROM Buzz b WHERE b.displayStatus = :displayStatus " +
            "AND b.pinned = :pinned ORDER BY b.id DESC";
    private final String QUERY_GET_LATEST_PINNED = "SELECT b FROM Buzz b WHERE b.pinned = :pinned " +
            "ORDER BY b.id DESC";

    private final String QUERY_GET_FLAGGED = "SELECT * FROM buzz WHERE flagged = ? AND " +
            "buzz_time > CURRENT_DATE - INTERVAL 1 DAY";
    private final String QUERY_GET_PINNED = "SELECT * FROM buzz WHERE pinned = ? AND " +
            "buzz_time > CURRENT_DATE - INTERVAL 1 DAY";
    private final String QUERY_GET_STATUS = "SELECT * FROM buzz WHERE display_status = ? AND " +
            "buzz_time > CURRENT_DATE - INTERVAL 1 DAY";

    private final String QUERY_GET_ACTIVE_BY_USER = "SELECT * FROM buzz WHERE user_id = ? AND " +
            "display_status = 1 AND buzz_time > CURRENT_DATE - INTERVAL 1 DAY";
    private final String QUERY_GET_PINNED_BY_USER = "SELECT * FROM buzz WHERE user_id = ? AND " +
            "pinned = ? AND buzz_time > CURRENT_DATE - INTERVAL 1 DAY";
    private final String QUERY_GET_FLAGGED_BY_USER = "SELECT * FROM buzz WHERE user_id = ? AND " +
            "flagged = ? AND buzz_time > CURRENT_DATE - INTERVAL 1 DAY";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Buzz saveOrUpdate(Buzz buzz) {
        if (buzz.getId() == 0) {
            em.persist(buzz);
            em.flush();
        } else {
            return em.merge(buzz);
        }

        return buzz;
    }

    @Override
    public List<Buzz> getAll() {
        return em.createNamedQuery("buzz.getAll", Buzz.class).getResultList();
    }

    @Override
    public Buzz getById(int buzzId) {
        return em.find(Buzz.class, buzzId);
    }

    @Override
    public List<Buzz> getLatest(int range) {
        return em.createQuery(QUERY_GET_LATEST, Buzz.class)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .setParameter("pinned", false)
                .setMaxResults(range)
                .getResultList();
    }

    @Override
    public List<Buzz> getLatestPinnedBuzz(int range) {
        return em.createQuery(QUERY_GET_LATEST_PINNED, Buzz.class)
                .setParameter("pinned", true)
                .setMaxResults(range)
                .getResultList();
    }

    @Override
    public int getActiveCountByUser(User user) {
        Query countQuery = em.createNativeQuery(Utils.convertQueryStringForCount(QUERY_GET_ACTIVE_BY_USER));
        countQuery.setParameter(1, user.getId());

        return ((BigInteger) countQuery.getSingleResult()).intValue();
    }

    @Override
    public int getPinnedCountByUser(User user) {
        Query countQuery = em.createNativeQuery(Utils.convertQueryStringForCount(QUERY_GET_PINNED_BY_USER));
        countQuery.setParameter(1, user.getId());
        countQuery.setParameter(2, true);

        return ((BigInteger) countQuery.getSingleResult()).intValue();
    }

    @Override
    public int getFlaggedCountByUser(User user) {
        Query countQuery = em.createNativeQuery(Utils.convertQueryStringForCount(QUERY_GET_FLAGGED_BY_USER));
        countQuery.setParameter(1, user.getId());
        countQuery.setParameter(2, true);

        return ((BigInteger) countQuery.getSingleResult()).intValue();
    }

    @Override
    public int getActiveCount() {
        Query countQuery = em.createNativeQuery(Utils.convertQueryStringForCount(QUERY_GET_STATUS));
        countQuery.setParameter(1, 1);

        return ((BigInteger) countQuery.getSingleResult()).intValue();
    }

    @Override
    public int getPinnedCount() {
        Query countQuery = em.createNativeQuery(Utils.convertQueryStringForCount(QUERY_GET_PINNED));
        countQuery.setParameter(1, true);

        return ((BigInteger) countQuery.getSingleResult()).intValue();
    }

    @Override
    public int getFlaggedCount() {
        Query countQuery = em.createNativeQuery(Utils.convertQueryStringForCount(QUERY_GET_FLAGGED));
        countQuery.setParameter(1, true);

        return ((BigInteger) countQuery.getSingleResult()).intValue();
    }

    @Override
    public int getInactiveCount() {
        Query countQuery = em.createNativeQuery(Utils.convertQueryStringForCount(QUERY_GET_STATUS));
        countQuery.setParameter(1, 2);

        return ((BigInteger) countQuery.getSingleResult()).intValue();
    }

    @Override
    public List<Buzz> getActiveByUser(User user) {
        Query query = em.createNativeQuery(QUERY_GET_ACTIVE_BY_USER, Buzz.class);
        query.setParameter(1, user.getId());

        return (List<Buzz>) query.getResultList();
    }

    @Override
    public List<Buzz> getPinnedByUser(User user) {
        Query query= em.createNativeQuery(QUERY_GET_PINNED_BY_USER, Buzz.class);
        query.setParameter(1, user.getId());
        query.setParameter(2, true);

        return (List<Buzz>) query.getResultList();
    }

    @Override
    public List<Buzz> getFlaggedByUser(User user) {
        Query query= em.createNativeQuery(QUERY_GET_FLAGGED_BY_USER, Buzz.class);
        query.setParameter(1, user.getId());
        query.setParameter(2, true);

        return (List<Buzz>) query.getResultList();
    }

    @Override
    public List<Buzz> getActive() {
        Query query= em.createNativeQuery(QUERY_GET_STATUS, Buzz.class);
        query.setParameter(1, 1);

        return (List<Buzz>) query.getResultList();
    }

    @Override
    public List<Buzz> getPinned() {
        Query query= em.createNativeQuery(QUERY_GET_PINNED, Buzz.class);
        query.setParameter(1, true);

        return (List<Buzz>) query.getResultList();
    }

    @Override
    public List<Buzz> getFlagged() {
        Query query= em.createNativeQuery(QUERY_GET_FLAGGED, Buzz.class);
        query.setParameter(1, true);

        return (List<Buzz>) query.getResultList();
    }

    @Override
    public List<Buzz> getInactive() {
        Query query= em.createNativeQuery(QUERY_GET_STATUS, Buzz.class);
        query.setParameter(1, 2);

        return (List<Buzz>) query.getResultList();
    }
}