package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Activity;
import net.therap.hyperbee.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * @author rayed
 * @since 11/28/16 10:22 AM
 */
@Repository
public class ActivityDaoImpl implements ActivityDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void create(Activity activity) {
        em.persist(activity);
    }

    @Override
    public Activity findById(int id) {
        return null;
    }

    @Override
    public Activity findByUserId(int userId) {
        Activity activity = null;

        try {
            activity = em.createQuery("SELECT a FROM Activity a WHERE a.user.id = :userId", Activity.class)
                            .setParameter("userId", userId)
                            .getSingleResult();
        } catch (NoResultException e){
            e.printStackTrace();
        }

        return activity;
    }
}
