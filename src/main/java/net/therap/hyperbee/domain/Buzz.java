package net.therap.hyperbee.domain;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bashir
 * @author zoha
 * @author rayed
 * @author duity
 * @author azim
 * @since 11/21/16
 */
@Entity
@Table(name = "buzz")
public class Buzz implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 160)
    private String message;

    @Column(name = "buzz_time", nullable = false)
    private DateTime buzzTime;

    @Column(nullable = false)
    private int active;

    @Column(nullable = false)
    private int pinned;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateTime getBuzzTime() {
        return buzzTime;
    }

    public void setBuzzTime(DateTime buzzTime) {
        this.buzzTime = buzzTime;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getPinned() {
        return pinned;
    }

    public void setPinned(int pinned) {
        this.pinned = pinned;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
