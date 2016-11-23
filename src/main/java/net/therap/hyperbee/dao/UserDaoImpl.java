package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:49 AM
 */

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void createUser(User user) {
        em.persist(user);
    }

    @Override
    public User findById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        User user = null;

        try {
            user = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User findByUsernameAndPassword(User user) {
        User retrievedUser = null;

        try {
            retrievedUser = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                    .setParameter("username", user.getUsername())
                    .setParameter("password", user.getPassword())
                    .getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return retrievedUser;
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public void updateUser(User user) {

    }

    @Override
    @Transactional
    public void deleteUser(int id) {

    }
}
