package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.Reservation;

import java.util.List;

/**
 * @author rumman
 * @since 11/29/16
 */
public interface ReservationService {

    void saveReservation(Reservation reservation);

    Reservation findReservationById(int reservationId);

    List<Reservation> findAllReservation();

    void delete(int reservationId);
}
