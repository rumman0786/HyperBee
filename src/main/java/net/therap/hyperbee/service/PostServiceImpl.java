package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.PostDao;
import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Post;
import net.therap.hyperbee.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author azim
 * @since 11/27/16
 */
@Repository
public class PostServiceImpl implements PostService {

    @Autowired
    private UserService userService;

    @Autowired
    private HiveService hiveService;

    @Autowired
    private PostDao postDao;

    @Override
    @Transactional
    public void savePost(int userId, int hiveId, Post post) {
        User user = userService.findById(userId);
        Hive hive = hiveService.findById(hiveId);
        post.setUser(user);
        post.setHive(hive);
        postDao.insertPost(post);
    }

    @Override
    public List<Post> getPostListByHive(int id) {

        return postDao.getPostListByHive(id);
    }
}
