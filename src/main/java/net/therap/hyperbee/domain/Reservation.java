package net.therap.hyperbee.domain;

import net.therap.hyperbee.domain.enums.ReservationStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static net.therap.hyperbee.utils.constant.DomainConstant.DATE_TIME_FIELD;
import static net.therap.hyperbee.utils.constant.DomainConstant.RES_STATUS_ENUM;

/**
 * @author bashir
 * @author rumman
 * @author rayed
 * @author azim
 * @author zoha
 * @since 11/21/16
 */
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "res_status", columnDefinition = RES_STATUS_ENUM)
    private ReservationStatus reservationStatus;

    @Column(columnDefinition = DATE_TIME_FIELD)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm a")
    private Calendar reservationFrom;

    @Column(columnDefinition = DATE_TIME_FIELD)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm a")
    private Calendar reservationTo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "conf_id")
    private ConferenceRoom conferenceRoom;

    public Reservation() {
//        this.reservationFrom = new GregorianCalendar();
//        this.reservationTo = new GregorianCalendar();
    }

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

    public Calendar getReservationFrom() {
        return reservationFrom;
    }

    public void setReservationFrom(Calendar reservationFrom) {
        this.reservationFrom = reservationFrom;
    }

    public Calendar getReservationTo() {
        return reservationTo;
    }

    public void setReservationTo(Calendar reservationTo) {
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

    public boolean isNew() {
        return id == 0;
    }
}
