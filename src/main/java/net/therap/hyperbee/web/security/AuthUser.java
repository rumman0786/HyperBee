package net.therap.hyperbee.web.security;

import net.therap.hyperbee.domain.enums.RoleType;

import java.io.Serializable;

/**
 * @author rayed
 * @since 11/23/16 1:56 PM
 */


public class AuthUser implements Serializable{

    private static final long serialVersionUID = 1;

    private int userId;
    private String username;
    private RoleType userRole;

    public AuthUser(int userId, RoleType role, String username) {
        this.userId = userId;
        this.username = username;
        this.userRole = role;
    }

    public AuthUser() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public RoleType getUserRole() {
        return userRole;
    }

    public void setUserRole(RoleType userRole) {
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
