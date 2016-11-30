package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author azim
 * @since 11/27/16
 */
@Repository
public class PostDaoImpl implements PostDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insertPost(Post post) {
        em.persist(em.merge(post));
        em.flush();
    }

    @Override
    public List<Post> getPostListByHive(int id) {

        return em.createQuery("SELECT p FROM Post p WHERE p.hive.id =:id", Post.class).setParameter("id", id)
                .getResultList();
    }
}
