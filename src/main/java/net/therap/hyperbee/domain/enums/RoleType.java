package net.therap.hyperbee.domain.enums;

import net.therap.hyperbee.utils.constant.DomainConstant;

import static net.therap.hyperbee.utils.constant.DomainConstant.ADMIN_ROLE;

/**
 * @author bashir
 * @since 11/21/16
 */
public enum RoleType {

    USER(DomainConstant.USER_ROLE), ADMIN(ADMIN_ROLE);

    private String role;

    RoleType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
