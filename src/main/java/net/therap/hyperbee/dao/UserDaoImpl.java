package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author rayed
 * @since 11/22/16 10:49 AM
 */

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        List<User> resultList = (List<User>) entityManager.createQuery("select u from User u where u.username like :username")
                .setParameter("username", username)
                .getResultList();
        return resultList.get(0);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(int id) {

    }
}
