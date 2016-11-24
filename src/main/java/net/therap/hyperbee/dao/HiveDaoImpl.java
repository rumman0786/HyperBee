package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Hive;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

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
}
