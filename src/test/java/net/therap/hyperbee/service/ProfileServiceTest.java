package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.ProfileDao;
import net.therap.hyperbee.domain.Profile;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author duity
 * @since 12/6/16.
 */
public class ProfileServiceTest {

    @InjectMocks
    private ProfileService profileService;

    @Mock
    private ProfileDao profileDao;

    Profile profile = new Profile();

    @Before
    public void setup() {
        profileService = new ProfileServiceImpl();
        MockitoAnnotations.initMocks(this);

        when(profileDao.save(profile, 1))
                .thenReturn(profile);
    }

    @Test
    public void update() {
        assertEquals(profile, profileDao.save(profile, 1));
    }

}
