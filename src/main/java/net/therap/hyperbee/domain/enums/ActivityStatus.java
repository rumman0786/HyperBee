package net.therap.hyperbee.domain.enums;

/**
 * @author bashir
 * @since 11/21/16
 */
public enum ActivityStatus {

    INACTIVE(0), ACTIVE(1);

    private int status;

    ActivityStatus(int status) {
        this.status = status;
    }


    public int getStatus() {
        return status;
    }
}
