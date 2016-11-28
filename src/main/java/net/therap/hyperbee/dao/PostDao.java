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

    public void insertPost(Post post);

    public List<Post> getPostListByHive(int id);
}
