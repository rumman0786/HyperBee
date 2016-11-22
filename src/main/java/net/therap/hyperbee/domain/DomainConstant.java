package net.therap.hyperbee.domain;

/**
 * @author bashir
 * @since 11/21/16
 */
public interface DomainConstant {

    String ACTIVE = "ACTIVE";
    String INACTIVE = "INACTIVE";
    String ADMIN_ROLE = "ADMIN";
    String USER_ROLE = "USER";
    String DISPLAY_STATUS_ENUM = "ENUM('ACTIVE,INACTIVE')";
    String DISPLAY_STATUS_FIELD = "display_status";
    String APPROVED = "APPROVED";
    String REJECTED = "REJECTED";
    String PENDING = "PENDING";
    String RES_STATUS_ENUM = "ENUM('APPROVED','REJECTED', 'PENDING')";
    String DATE_TIME_FIELD = "DATETIME";
}
