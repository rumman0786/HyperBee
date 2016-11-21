package net.therap.hyperbee.domain.enums;

/**
 * @author bashir
 * @since 11/21/16
 */
public enum RoleType {

    USER("USER"), ADMIN("ADMIN");

    private String role;

    RoleType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
