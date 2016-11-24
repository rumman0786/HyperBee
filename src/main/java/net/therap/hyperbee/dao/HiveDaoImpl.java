package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.User;
import net.therap.hyperbee.web.command.UserIdInfo;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author azim
 * @since 11/22/16
 */
@Repository
public class HiveDaoImpl implements HiveDao {

    @PersistenceContext
    private EntityManager em;

    public void saveHive(Hive hive) {
        em.persist(hive);
        em.flush();
    }

    public List<Hive> retrieveHive() {
        List<Hive> hiveList = em.createQuery("FROM Hive", Hive.class).getResultList();

        for (Hive hive : hiveList) {
            Hibernate.initialize(hive.getPostList());
            Hibernate.initialize(hive.getUserList());
            Hibernate.initialize(hive.getNoticeList());
        }

        return hiveList;
    }

    public List<User> getUserListById(List<Integer> idList) {

        return em.createQuery("SELECT u FROM User u WHERE u.id IN :userIdList", User.class)
                .setParameter("userIdList", idList).getResultList();

    }

    public List<User> getUserList() {

        return em.createQuery(" SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public Hive retrieveHiveById(int id) {
        return em.find(Hive.class,id);
    }

    @Override
    public void insertUsersToHive(Hive hive, List<User> userList) {
        hive.getUserList().addAll(userList);
        em.flush();
    }

    @Override
    public List<Hive> getHiveListByUserId(int userId) {

        User user = em.find(User.class, userId);

        Hibernate.initialize(user.getHiveList());

        return user.getHiveList();
    }

}
