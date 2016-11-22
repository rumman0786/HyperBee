package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Buzz;
import net.therap.hyperbee.domain.enums.DisplayStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
@Repository
public class BuzzDaoImpl implements BuzzDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public boolean create(Buzz newBuzz) {
        entityManager.persist(newBuzz);
        return true;
    }

    @Override
    public List<Buzz> retrieveAll() {
        String queryString = "SELECT b FROM Buzz b WHERE b.display_status = :displayStatus";

        Query retrievalQuery = entityManager.createQuery(queryString);
        retrievalQuery.setParameter("displayStatus", DisplayStatus.ACTIVE);

        return retrievalQuery.getResultList();
    }

    @Override
    public Buzz retrieveById(int buzzId) {
        return entityManager.find(Buzz.class, buzzId);
    }

    @Override
    public List<Buzz> retrieveByUser(int userId) {
        String queryString = "SELECT b FROM Buzz b WHERE b.user_id = :userId";

        Query retrievalQuery = entityManager.createQuery(queryString);
        retrievalQuery.setParameter("userId", userId);

        return retrievalQuery.getResultList();
    }

    @Override
    public List<Buzz> retrieveByDisplayStatus(DisplayStatus displayStatus) {
        String queryString = "SELECT b FROM Buzz b WHERE b.display_status = :displayStatus";

        Query retrievalQuery = entityManager.createQuery(queryString);
        retrievalQuery.setParameter("displayStatus", displayStatus.getStatus());

        return retrievalQuery.getResultList();
    }

    @Override
    public List<Buzz> retrieveLatest(int range) {
        String queryString = "SELECT b FROM Buzz b WHERE b.display_status = :displayStatus ORDER BY b.id DESC";

        Query retrievalQuery = entityManager.createQuery(queryString);
        retrievalQuery.setParameter("displayStatus", DisplayStatus.ACTIVE);

        return retrievalQuery.setMaxResults(range).getResultList();
    }

    @Override
    public Buzz update(Buzz buzzToUpdate) {
        return entityManager.merge(buzzToUpdate);
    }

    @Override
    @Transactional
    public Buzz delete(Buzz buzzToDelete) {
        return entityManager.merge(buzzToDelete);
    }
}
