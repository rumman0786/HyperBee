package net.therap.hyperbee.domain.enums;

import net.therap.hyperbee.domain.DomainConstant;

/**
 * @author bashir
 * @since 11/21/16
 */
public enum RoleType {

    USER(DomainConstant.USER), ADMIN(DomainConstant.ADMIN);

    private String role;

    RoleType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
