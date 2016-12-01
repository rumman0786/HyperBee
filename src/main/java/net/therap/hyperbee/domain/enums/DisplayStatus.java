package net.therap.hyperbee.domain.enums;

import static net.therap.hyperbee.utils.constant.Constant.DISPLAY_ACTIVE;
import static net.therap.hyperbee.utils.constant.Constant.DISPLAY_INACTIVE;

/**
 * @author bashir
 * @author rayed
 * @since 11/21/16
 */
public enum DisplayStatus {

    INACTIVE(DISPLAY_INACTIVE), ACTIVE(DISPLAY_ACTIVE);

    private String status;

    DisplayStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
