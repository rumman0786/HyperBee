package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author azim
 * @since 11/27/16
 */
@Repository
public class PostDaoImpl implements PostDao {

    private final String QUERY_GET_POST_ID = "SELECT p FROM Post p WHERE p.hive.id =:id";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void savePost(Post post) {
        em.persist(post);
        em.flush();
    }
}
