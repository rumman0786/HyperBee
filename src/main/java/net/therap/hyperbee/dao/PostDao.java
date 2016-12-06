package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Post;
import org.springframework.stereotype.Repository;

/**
 * @author azim
 * @since 11/27/16
 */
@Repository
public interface PostDao {

    void savePost(Post post);
}
