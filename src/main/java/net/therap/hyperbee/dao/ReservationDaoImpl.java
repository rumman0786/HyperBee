package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author rumman
 * @since 11/29/16
 */
@Repository
public class ReservationDaoImpl implements ReservationDao {

    private static final String RESERVATION_ALL_QUERY = "FROM Reservation";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void saveOrUpdate(Reservation reservation) {
        if (reservation.isNew()) {
            em.persist(reservation);
            em.flush();
        } else {
            em.merge(reservation);
        }
    }

    @Override
    public Reservation findById(int reservationId) {
        return em.find(Reservation.class, reservationId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Reservation> findAll() {
        return em.createQuery(RESERVATION_ALL_QUERY).getResultList();
    }

    @Override
    @Transactional
    public void delete(int reservationId) {
        Reservation attachedReservation = em.getReference(Reservation.class, reservationId);
        em.remove(attachedReservation);
    }
}
