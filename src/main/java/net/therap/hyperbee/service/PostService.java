package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Post;
import org.springframework.stereotype.Repository;

/**
 * @author azim
 * @since 11/27/16
 */
@Repository
public interface PostService {

   void savePost(int userId, int hiveId, Post post);
}
