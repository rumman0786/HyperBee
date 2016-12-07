package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Profile;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.service.ProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.Assert.assertEquals;

/**
 * @author duity
 * @since 12/4/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private ProfileService profileService;

    @Test
    public void testSaveProfileForUser() throws Exception {
        User user = new User();
        Profile profile = new Profile();
        profile.setDesignation("Developer");

        MockMultipartFile profilePicture = new MockMultipartFile("testFile1", new byte[1]);
        MockMultipartFile coverPicture = new MockMultipartFile("testFile2", new byte[1]);

        Mockito.when(profileService.saveFileForUser(coverPicture, profilePicture, user, profile)).thenReturn(profile);
        assertEquals(profileService.saveFileForUser(coverPicture, profilePicture, user, profile), profile);

        Mockito.when(profileService.saveProfileForUser(profile, 1)).thenReturn("profile is saved");
        assertEquals(profileService.saveProfileForUser(profile, 1), "profile is saved");
    }
}



