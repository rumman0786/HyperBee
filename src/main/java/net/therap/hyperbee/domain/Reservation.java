package net.therap.hyperbee.domain;

import net.therap.hyperbee.domain.enums.ReservationStatus;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

import static net.therap.hyperbee.domain.constant.DomainConstant.DATE_TIME_FIELD;
import static net.therap.hyperbee.domain.constant.DomainConstant.RES_STATUS_ENUM;

/**
 * @author bashir
 * @author rumman
 * @author rayed
 * @author azim
 * @since 11/21/16
 */
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "res_status", columnDefinition = RES_STATUS_ENUM)
    private ReservationStatus reservationStatus;

    @Column(columnDefinition = DATE_TIME_FIELD)
    private DateTime reservationFrom;

    @Column(columnDefinition = DATE_TIME_FIELD)
    private DateTime reservationTo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "conf_id")
    private ConferenceRoom conferenceRoom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public DateTime getReservationFrom() {
        return reservationFrom;
    }

    public void setReservationFrom(DateTime reservationFrom) {
        this.reservationFrom = reservationFrom;
    }

    public DateTime getReservationTo() {
        return reservationTo;
    }

    public void setReservationTo(DateTime reservationTo) {
        this.reservationTo = reservationTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ConferenceRoom getConferenceRoom() {
        return conferenceRoom;
    }

    public void setConferenceRoom(ConferenceRoom conferenceRoom) {
        this.conferenceRoom = conferenceRoom;
    }
}
