package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.Notice;
import net.therap.hyperbee.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
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

    private final String QUERY_GET_USER_BY_ID = "SELECT u FROM User u WHERE u.id IN :userIdList";

    private final String QUERY_GET_HIVE_BY_HIVENAME = "SELECT h FROM Hive h WHERE h.name=:hiveName";

    private final String QUERY_GET_USER_NOTIN_LIST = "SELECT u FROM User u WHERE u NOT IN :userList";

    private final String QUERY_GET_LAST_FIVE_NOTICE = "SELECT n FROM Notice n WHERE n IN :noticeList " + "ORDER BY n.id DESC";

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveHive(Hive hive) {
        List<Hive> hiveList = em.createQuery(QUERY_GET_HIVE_BY_HIVENAME, Hive.class)
                .setParameter("hiveName", hive.getName()).getResultList();
        if (hiveList.size() == 0) {
            em.persist(hive);
            em.flush();
        }
    }

    public List<User> getUserListById(List<Integer> idList) {
        return em.createQuery(QUERY_GET_USER_BY_ID, User.class)
                .setParameter("userIdList", idList).getResultList();
    }

    @Override
    public Hive retrieveHiveById(int id) {
        return em.find(Hive.class, id);
    }

    @Override
    @Transactional
    public void addUsersToHive(Hive hive, List<User> userList) {
        hive.getUserList().addAll(userList);
        em.flush();
    }

    public Hive getHiveByHiveName(String name) {
        return em.createQuery(QUERY_GET_HIVE_BY_HIVENAME, Hive.class)
                .setParameter("hiveName", name).getResultList().get(0);
    }

    @Override
    public List<User> findUserNotInList(List<User> userList) {
        return em.createQuery(QUERY_GET_USER_NOTIN_LIST, User.class)
                .setParameter("userList", userList).getResultList();
    }

    @Override
    public Hive findById(int hiveId) {
        return em.find(Hive.class, hiveId);
    }

    @Override
    @Transactional
    public void removeUsersFromHive(Hive hive, List<User> userList) {
        hive.getUserList().removeAll(userList);
        em.flush();
    }

    @Override
    public List<Notice> getLastFiveNotice(List<Notice> noticeList, int range) {
        return em.createQuery(QUERY_GET_LAST_FIVE_NOTICE, Notice.class)
                .setParameter("noticeList", noticeList).getResultList();
    }

    @Override
    public List<Hive> findAll() {
        return em.createQuery("From Hive", Hive.class).getResultList();
    }

    @Override
    @Transactional
    public boolean alreadyExist(Hive hive) {
        List<Hive> hiveList = em.createQuery(QUERY_GET_HIVE_BY_HIVENAME, Hive.class)
                .setParameter("hiveName", hive.getName()).getResultList();

        if (hiveList.size() != 0) {
            return true;
        }

        return false;
    }
}
