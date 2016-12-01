package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author azim
 * @since 11/27/16
 */
@Repository
public interface PostDao {

    void insertPost(Post post);

    List<Post> getPostListByHive(int id);
}
