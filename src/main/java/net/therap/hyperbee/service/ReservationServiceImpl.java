package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.ReservationDao;
import net.therap.hyperbee.domain.Reservation;
import net.therap.hyperbee.web.helper.ReservationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rumman
 * @since 11/29/16
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private ReservationHelper reservationHelper;

    @Override
    @Transactional
    public void saveReservation(Reservation reservation) {
        reservationDao.saveOrUpdate(reservation);
        reservationHelper.updateReservationCache();
    }

    @Override
    public Reservation findReservationById(int reservationId) {
        return reservationDao.findById(reservationId);
    }

    @Override
    public List<Reservation> findAllReservation() {
        return reservationDao.findAll();
    }

    @Override
    @Transactional
    public void delete(int reservationId) {
        reservationDao.delete(reservationId);
        reservationHelper.updateReservationCache();
    }

    @Override
    public List<Reservation> findLatestReservation(int range) {
        return reservationDao.findLatestReservation(range);
    }
}
