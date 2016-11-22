package net.therap.hyperbee.domain.enums;

import net.therap.hyperbee.domain.constant.DomainConstant;

/**
 * @author bashir
 * @since 11/21/16
 */
public enum ReservationStatus {

    APPROVED(DomainConstant.APPROVED),
    REJECTED(DomainConstant.REJECTED),
    PENDING(DomainConstant.PENDING);

    private String reservationStatus;

    ReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }
}
