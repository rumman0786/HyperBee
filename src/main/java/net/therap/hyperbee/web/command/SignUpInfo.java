package net.therap.hyperbee.web.command;

import net.therap.hyperbee.domain.User;

/**
 * @author rayed
 * @since 11/27/16 12:01 PM
 */
public class SignUpInfo {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password1;

    private String password2;

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {

        return password1;
    }

    public void setPassword1(String password1) {

        this.password1 = password1;
    }

    public String getPassword2() {

        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public User getUser() {
        User user = new User();

        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setUsername(getUsername());
        user.setEmail(getEmail());
        user.setPassword(getPassword1());

        return user;
    }
}
