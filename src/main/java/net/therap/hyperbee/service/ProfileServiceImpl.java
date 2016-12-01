package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.Profile;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.web.helper.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static net.therap.hyperbee.utils.constant.Messages.PROFILE_SAVE_MESSAGE;

/**
 * @author duity
 * @since 11/22/16.
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ImageUploader imageUploader;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public String saveProfileForUser(Profile profile, int userId) {
        User user = userDao.findById(userId);

        if (user.getProfile() == null) {
            user.setProfile(profile);
            em.persist(user);
            em.flush();
        } else {
            user.setProfile(profile);
            em.merge(user);
            em.flush();
        }

        return PROFILE_SAVE_MESSAGE;
    }

    @Override
    public Profile saveFileForUser(MultipartFile coverFile, MultipartFile profileFile, User user, Profile profile) {

        if (profileFile.getSize() == 0) {
            profile.setImagePath(user.getProfile().getImagePath());
        } else {
            String filename = user.getUsername().replaceAll(" ", "") + profileFile.getOriginalFilename();
            profile.setImagePath(filename);
            if (!profileFile.isEmpty()) {
                imageUploader.createImagesDirIfNeeded();
                imageUploader.createImage(filename, profileFile);
            }
        }

        if (coverFile.getSize() == 0) {
            profile.setCoverImage(user.getProfile().getCoverImage());
        } else {
            String coverImageName = user.getUsername().replaceAll(" ", "") + coverFile.getOriginalFilename();
            profile.setCoverImage(coverImageName);
            if (!coverFile.isEmpty()) {
                imageUploader.createImagesDirIfNeeded();
                imageUploader.createImage(coverImageName, coverFile);
            }
        }

        return profile;
    }
}
