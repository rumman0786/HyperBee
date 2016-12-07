package net.therap.hyperbee.web.command;

import net.therap.hyperbee.domain.User;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author rayed
 * @since 11/27/16 12:01 PM
 */
public class SignUpDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 1, message = "{first.name.required}")
    private String firstName;

    @Size(min = 1, message = "{last.name.required}")
    private String lastName;

    @Size(min = 1, message = "{username.required}")
    private String username;

    @Size(min = 1, message = "{email.required}")
    private String email;

    @Size(min = 1, message = "{password.required}")
    private String password1;

    @Size(min = 1, message = "{password.verify}")
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
