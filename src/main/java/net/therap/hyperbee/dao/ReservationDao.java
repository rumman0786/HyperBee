package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Reservation;

import java.util.List;

/**
 * @author rumman
 * @since 11/29/16
 */
public interface ReservationDao {

    void saveOrUpdate(Reservation reservation);

    Reservation findById(int reservationId);

    List<Reservation> findAll();

    void delete(int reservationId);
}
