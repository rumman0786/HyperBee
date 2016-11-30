package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Activity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author rayed
 * @since 11/28/16 10:22 AM
 */
@Repository
public class ActivityDaoImpl implements ActivityDao {

    private static String SELECT_ACTIVITY = "SELECT a FROM Activity a WHERE a.user.id = :userId ORDER BY a.activityTime DESC";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void create(Activity activity) {
        em.persist(activity);
        em.flush();
    }

    @Override
    public List<Activity> findByUserId(int userId) {
        List<Activity> activityList = null;

        try {
            activityList = em.createQuery(SELECT_ACTIVITY, Activity.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return activityList;
    }
}
