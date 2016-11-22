package net.therap.hyperbee.domain.constant;

/**
 * @author bashir
 * @since 11/21/16
 */
public interface DomainConstant {

    String DISPLAY_ACTIVE = "ACTIVE";
    String DISPLAY_INACTIVE = "INACTIVE";
    String ADMIN_ROLE = "ADMIN";
    String USER_ROLE = "USER";
    String DISPLAY_STATUS_ENUM = "ENUM('ACTIVE','INACTIVE') default 'ACTIVE'";
    String DISPLAY_STATUS_FIELD = "display_status";
    String APPROVED = "APPROVED";
    String REJECTED = "REJECTED";
    String PENDING = "PENDING";
    String RES_STATUS_ENUM = "ENUM('APPROVED','REJECTED','PENDING')";
    String DATE_TIME_FIELD = "DATETIME";
    String HIGH_PRIORITY = "HIGH";
    String LOW_PRIORITY = "LOW";
    String MEDIUM_PRIORITY = "MEDIUM";
    String PRIORITY_ENUM = "enum('HIGH','LOW','MEDIUM')";
}
