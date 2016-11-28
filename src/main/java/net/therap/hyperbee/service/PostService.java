package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author azim
 * @since 11/27/16
 */
@Repository
public interface PostService {

    public void savePost(int userId, int hiveId, Post post);

    public List<Post> getPostListByHive(int id);
}
