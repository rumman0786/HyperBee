package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Profile;
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
    private EntityManager entityManager;

    @Transactional
    public Profile save(Profile profile) {
        entityManager.persist(profile);

        return profile;
    }
}