package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author duity
 * @since 11/22/16.
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Profile getProfile(int id) {
        return em.find(Profile.class, id);
    }
}
