package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Profile;

/**
 * @author duity
 * @since 11/22/16.
 */
public interface ProfileDao {

    Profile save(Profile profile, int userId);
}
