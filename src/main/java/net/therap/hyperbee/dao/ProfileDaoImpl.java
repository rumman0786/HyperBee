package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Profile;
import net.therap.hyperbee.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author duity
 * @since 11/22/16.
 */
@Repository
public class ProfileDaoImpl implements ProfileDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDao userDao;

    @Transactional
    public Profile save(Profile profile, int userId) {
        User user = userDao.findById(userId);

        if (user.getProfile() == null) {
            user.setProfile(profile);
            em.persist(user);
            em.flush();
        } else {
            user.setProfile(profile);
            em.merge(user);
        }

        return profile;
    }
}
