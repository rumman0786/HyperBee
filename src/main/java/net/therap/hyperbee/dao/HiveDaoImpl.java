package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Hive;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author azim
 * @since 11/22/16
 */
@Repository
public class HiveDaoImpl implements HiveDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void insertHive(Hive hive) {
        entityManager.persist(hive);
        entityManager.flush();
    }
}
