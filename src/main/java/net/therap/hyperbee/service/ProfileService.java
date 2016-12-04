package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Profile;
import net.therap.hyperbee.domain.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author duity
 * @since 11/22/16.
 */
public interface ProfileService {

    String saveProfileForUser(Profile profile, int userId);

    Profile saveFileForUser(MultipartFile coverFile, MultipartFile profileFile, User user, Profile profile);
}
