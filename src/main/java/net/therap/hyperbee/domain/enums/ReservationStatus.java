package net.therap.hyperbee.domain.enums;

import net.therap.hyperbee.utils.constant.DomainConstant;

/**
 * @author bashir
 * @author rumman
 * @since 11/21/16
 */
public enum ReservationStatus {

    APPROVED(DomainConstant.APPROVED),
    REJECTED(DomainConstant.REJECTED),
    PENDING(DomainConstant.PENDING);

    private String status;

    ReservationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
