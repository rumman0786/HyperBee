package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static net.therap.hyperbee.utils.constant.Messages.PROFILE_SAVE_MESSAGE;

/**
 * @author duity
 * @since 11/22/16.
 */
@Repository
public class ProfileDaoImpl implements ProfileDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public String save(Profile profile) {
        em.persist(profile);

        return PROFILE_SAVE_MESSAGE ;
    }


}
