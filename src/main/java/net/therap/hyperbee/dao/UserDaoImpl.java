package net.therap.hyperbee.dao;


import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.domain.enums.DisplayStatus;
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

    private static String FIND_BY_USERNAME = "SELECT u FROM User u WHERE u.username = :username";

    private static String FIND_BY_USERNAME_AND_PASSWORD = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";

    private static String FIND_BY_USERNAME_AND_DISPLAYSTATUS = "SELECT u FROM User u WHERE u.username = :username AND u.displayStatus = :status";

    private static String FIND_USER_ACTIVE = "SELECT u FROM User u WHERE u.displayStatus = :status";

    private static String FIND_ALL = "SELECT u FROM User u";

    private static String FIND_BY_USRNAME_AND_EMAIL = "User.findByUsernameOrEmail";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User createUser(User user) {
        em.persist(user);
        em.flush();

        return user;
    }

    @Override
    public User findById(int id) {

        return em.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        User user = null;

        try {
            user = em.createQuery(FIND_BY_USERNAME, User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User findByUsernameOrEmail(String username, String email) {
        User user = null;

        try {
            user = em.createNamedQuery(FIND_BY_USRNAME_AND_EMAIL, User.class)
                    .setParameter("username", username)
                    .setParameter("email", email)
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
            retrievedUser = em.createQuery(FIND_BY_USERNAME_AND_PASSWORD, User.class)
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

        return em.createQuery(FIND_ALL, User.class).getResultList();
    }

    @Override
    public List<User> findActiveUser() {

        return em.createQuery(FIND_USER_ACTIVE, User.class)
                .setParameter("status", DisplayStatus.ACTIVE)
                .getResultList();
    }

    @Override
    public List<User> searchUserByEntry(String entry) {
        return em.createNamedQuery("User.SearchUserByEntry")
                .setParameter("name", entry+"%")
                .getResultList();
    }

    @Override
    @Transactional
    public void updateUser(User user) {

    }

    @Override
    @Transactional
    public void deleteUser(int id) {

    }

    @Override
    @Transactional
    public void inactivate(int userId) {
        em.createQuery("UPDATE User u SET u.displayStatus = 'INACTIVE' WHERE id = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
        em.flush();
    }

    @Override
    @Transactional
    public void activate(int userId) {
        em.createQuery("UPDATE User u SET u.displayStatus = 'ACTIVE' WHERE id = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
        em.flush();
    }

    @Override
    public int findByDisplayStatus(DisplayStatus status) {
        return em.createQuery("SELECT u FROM User u WHERE u.displayStatus = :status", User.class)
                .setParameter("status", status)
                .getResultList().size();
    }
}
