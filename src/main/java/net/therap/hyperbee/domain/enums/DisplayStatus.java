package net.therap.hyperbee.domain.enums;

import net.therap.hyperbee.domain.DomainConstant;

/**
 * @author bashir
 * @since 11/21/16
 */
public enum DisplayStatus {

    INACTIVE(DomainConstant.INACTIVE), ACTIVE(DomainConstant.ACTIVE);

    private String status;

    DisplayStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
